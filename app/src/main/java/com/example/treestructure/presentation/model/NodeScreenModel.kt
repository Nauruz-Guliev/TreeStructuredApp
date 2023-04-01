package com.example.treestructure.presentation.model

data class NodeScreenModel (
    var parent: Node,
    var isRootNode: Boolean = false,
    var childNodes: List<Node>
)
