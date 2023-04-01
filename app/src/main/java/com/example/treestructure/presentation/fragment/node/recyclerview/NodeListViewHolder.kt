package com.example.treestructure.presentation.fragment.node.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treestructure.R
import com.example.treestructure.databinding.NodeItemBinding
import com.example.treestructure.domain.models.Node

class NodeListViewHolder(
    private val binding: NodeItemBinding,
    private val onNodeClicked: ((Long?) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Node) {
        with(binding) {
            binding.root.tag = item.id
            tvNodeName.text = item.name
            tvLevel.text = binding.root.context.getString(R.string.level, item.level.toString())
        }
        binding.root.setOnClickListener {
            onNodeClicked?.invoke(item.id)
        }
    }

    companion object {
        fun create(parent: ViewGroup, onNodeClicked: ((Long?) -> Unit)?) =
            NodeListViewHolder(
                binding = NodeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                onNodeClicked = onNodeClicked
            )
    }
}
