package com.example.treestructure.presentation.fragment.node.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treestructure.databinding.NodeItemBinding
import com.example.treestructure.presentation.model.Node
import java.util.zip.Inflater

class NodeListViewHolder(private val binding: NodeItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: Node) {
        with(binding) {
            tvNodeName.text = item.name
        }
    }

    companion object {
        fun create(parent: ViewGroup) =
            NodeListViewHolder(
                binding = NodeItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }
}