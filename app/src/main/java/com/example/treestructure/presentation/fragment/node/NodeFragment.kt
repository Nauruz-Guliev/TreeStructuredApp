package com.example.treestructure.presentation.fragment.node

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.treestructure.App
import com.example.treestructure.R
import com.example.treestructure.databinding.NodeFragmentBinding
import javax.inject.Inject

class NodeFragment : Fragment(R.layout.node_fragment) {

    private var _binding: NodeFragmentBinding? = null
    private val binding by lazy { _binding!! }
    @Inject
    lateinit var viewModel: NodeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = NodeFragmentBinding.inflate(layoutInflater)
    }
}