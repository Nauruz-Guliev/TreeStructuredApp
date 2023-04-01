package com.example.treestructure.presentation.fragment.node.recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.treestructure.domain.models.Node


class NodeListAdapter(
    private val onNodeClicked: ((Long?) -> Unit)?
) : ListAdapter<Node, NodeListViewHolder>(differ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeListViewHolder =
        NodeListViewHolder.create(parent, onNodeClicked)

    override fun onBindViewHolder(holder: NodeListViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    object differ : DiffUtil.ItemCallback<Node>() {
        override fun areItemsTheSame(oldItem: Node, newItem: Node): Boolean =
            oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Node, newItem: Node): Boolean =
            oldItem == newItem
    }
}
