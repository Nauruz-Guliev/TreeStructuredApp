package com.example.treestructure.presentation.fragment.root

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.treestructure.App
import com.example.treestructure.databinding.RootNodeFragmentBinding
import com.example.treestructure.presentation.base.BaseFragment
import kotlinx.coroutines.launch
import javax.inject.Inject

class RootNodeFragment : BaseFragment<RootNodeFragmentBinding>(
    RootNodeFragmentBinding::inflate
) {

    @Inject
    lateinit var viewModel: RootNodeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listenForChanges()
    }

    private fun listenForChanges() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { uiState ->
                    binding.cardRoot.visibility = when (uiState) {
                        null -> ViewGroup.GONE
                        else -> ViewGroup.VISIBLE
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCardClickListener()
    }

    private fun initCardClickListener() {
        binding.cardRoot.setOnClickListener {
            val id = viewModel.state.value!!.id
            RootNodeFragmentDirections.actionRootNodeFragmentToNodeFragment(id!!).also {
                findNavController().navigate(it)
            }
        }
    }
}
