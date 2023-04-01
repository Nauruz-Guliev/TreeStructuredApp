package com.example.treestructure.presentation.fragment.node.recyclerview

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class SwipeToDeleteHelper (
    private val onDeleteAction: (Any) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onDeleteAction(viewHolder.itemView.tag)
    }
}
