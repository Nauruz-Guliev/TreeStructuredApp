package com.example.treestructure.presentation.fragment.node

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.treestructure.App
import com.example.treestructure.R
import com.example.treestructure.databinding.NodeFragmentBinding
import com.example.treestructure.presentation.utils.HashNameGenerator
import com.example.treestructure.presentation.base.BaseFragment
import com.example.treestructure.presentation.fragment.node.recyclerview.NodeListAdapter
import com.example.treestructure.presentation.fragment.node.recyclerview.SwipeToDeleteHelper
import com.example.treestructure.domain.models.Node
import com.example.treestructure.presentation.model.state.NodeScreenModel
import com.example.treestructure.presentation.model.state.TreeStructureUiState
import com.example.treestructure.presentation.utils.showSnackBar
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class NodeFragment : BaseFragment<NodeFragmentBinding>(
    NodeFragmentBinding::inflate
) {
    private var rvAdapter: NodeListAdapter? = null
    private val args: NodeFragmentArgs by navArgs()
    private var parentId: Long? = null

    @Inject
    lateinit var viewModel: NodeViewModel

    @Inject
    lateinit var hashNameGenerator: HashNameGenerator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentId = args.childId
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateList()
        initButtonClickListener()
        initRecyclerView()
        listenForChanges()
    }

    private fun updateList() {
        viewModel.getNodes(parentId = parentId)
    }
    private fun listenForChanges() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { uiState ->
                    when (uiState) {
                        is TreeStructureUiState.Success<*> -> {
                            rvAdapter?.submitList((uiState.data as NodeScreenModel).childNodes)
                            hideAllSecondaryViews()
                        }
                        is TreeStructureUiState.Empty -> {
                            hideAllSecondaryViews()
                            showEmptyStateMessage()
                        }
                        is TreeStructureUiState.Error -> {
                            with(binding.root) {
                                showSnackBar(uiState.message.toString(this.context))
                            }
                        }
                        is TreeStructureUiState.Loading -> {
                            showProgressBar()
                        }
                        else -> {
                            //initial null state
                        }
                    }
                }
            }
        }
    }

    private fun hideAllSecondaryViews() {
        with(binding) {
            progressBar.visibility = ViewGroup.GONE
            tvEmptyState.visibility = ViewGroup.GONE
        }
    }

    private fun showEmptyStateMessage() {
        binding.tvEmptyState.visibility = ViewGroup.VISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = ViewGroup.VISIBLE
    }


    private fun initRecyclerView() {
        with(binding.rvNodes) {
            rvAdapter = NodeListAdapter(::onNodeClicked).also {
                adapter = it
            }
            ItemTouchHelper(SwipeToDeleteHelper(::onNodeDeleted))
                .attachToRecyclerView(this)
        }
    }

    private fun onNodeDeleted(id: Any) {
        viewModel.deleteNode(id)
    }

    private fun onNodeClicked(nodeId: Long?) {
        NodeFragmentDirections.actionNodeFragmentSelf(nodeId!!).also { navDirections ->
            findNavController().navigate(navDirections)
        }
    }

    private fun initButtonClickListener() {
        binding.btnFab.setOnClickListener {
            lifecycleScope.launch {
                when (val state = viewModel.state.value) {
                    is TreeStructureUiState.Success -> saveNode(state.data.parent!!)
                    is TreeStructureUiState.Empty -> saveNode(state.data.parent!!)
                    else -> binding.root.showSnackBar(getString(R.string.error_creating))
                }
            }
        }
    }

    private fun saveNode(parent: Node) {
        lifecycleScope.launch {
            viewModel.createNode(createNode(parent))
        }
    }

    private fun createNode(parent: Node): Node {
        return Node(
            level = parent.level.plus(1),
            createdAt = Calendar.getInstance().time,
            name = hashNameGenerator.generate(parent.hashCode()),
            parentId = parent.id
        )
    }
}
