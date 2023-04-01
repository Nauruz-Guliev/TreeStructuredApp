package com.example.treestructure.presentation.model.state

import com.example.treestructure.domain.models.Node

data class NodeScreenModel (
    var parent: Node?,
    var isRootNode: Boolean = false,
    var childNodes: List<Node>
)
