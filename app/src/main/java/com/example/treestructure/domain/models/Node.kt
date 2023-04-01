package com.example.treestructure.domain.models


import java.util.*

data class Node(
    var id: Long? = null,
    var name: String = "",
    var level: Int = 0,
    var parentId: Long? = null,
    var createdAt: Date? = null,
)
