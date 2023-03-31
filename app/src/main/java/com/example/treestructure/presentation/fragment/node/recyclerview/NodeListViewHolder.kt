package com.example.treestructure.presentation.fragment.node.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treestructure.databinding.NodeItemBinding
import com.example.treestructure.presentation.model.Node

class NodeListViewHolder(
    private val binding: NodeItemBinding,
    private val onNodeClicked: ((Long?) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Node) {
        with(binding) {
            tvNodeName.text = item.name
        }
        onNodeClicked?.invoke(item.id)
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
