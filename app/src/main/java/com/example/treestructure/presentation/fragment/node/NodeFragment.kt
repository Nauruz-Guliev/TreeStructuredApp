package com.example.treestructure.presentation.fragment.node

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.treestructure.App
import com.example.treestructure.databinding.NodeFragmentBinding
import com.example.treestructure.domain.util.HashNameGenerator
import com.example.treestructure.presentation.fragment.node.recyclerview.NodeListAdapter
import com.example.treestructure.presentation.model.Node
import com.example.treestructure.presentation.model.NodeModel
import com.example.treestructure.presentation.model.state.TreeStructureUiState
import com.example.treestructure.presentation.utils.showToast
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class NodeFragment : Fragment() {

    private var _binding: NodeFragmentBinding? = null
    private val binding by lazy { _binding!! }
    private var adapter: NodeListAdapter? = null

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NodeFragmentBinding.inflate(layoutInflater)
        parentId = args.childId
        updateList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                            adapter?.submitList((uiState.data as NodeModel).childNodes.toList())
                        }
                        is TreeStructureUiState.Empty -> {
                        }
                        is TreeStructureUiState.Error -> {
                        }
                        is TreeStructureUiState.Loading -> {

                        }
                    }
                }
            }
        }
    }


    private fun initRecyclerView() {
        adapter = NodeListAdapter(::onNodeClicked).also {
            binding.rvNodes.adapter = it
        }
    }

    private fun onNodeClicked(nodeId: Long?) {
        NodeFragmentDirections.actionNodeFragmentSelf(nodeId!!).also { navDirections ->
            findNavController().navigate(navDirections)
        }
    }

    private fun initButtonClickListener() {
        binding.btnFab.setOnClickListener {
            val parent = (viewModel.state.value as TreeStructureUiState.Success).data.parent
            lifecycleScope.launch {
                viewModel.createNode(createNode(parent))
            }
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
