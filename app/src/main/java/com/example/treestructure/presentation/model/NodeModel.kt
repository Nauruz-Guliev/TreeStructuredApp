package com.example.treestructure.presentation.model

data class NodeModel (
    var parent: Node,
    var isRootNode: Boolean = false,
    var childNodes: List<Node>
)
