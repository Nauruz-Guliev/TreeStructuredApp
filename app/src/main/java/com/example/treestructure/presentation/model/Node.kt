package com.example.treestructure.presentation.model


import com.example.treestructure.data.utils.converter.DateConverter
import java.util.*

data class Node(
    var id: Long = 0,
    var name: String = "",
    var level: Int = 0,
    var parentId: Long? = 0,
    var createdAt: Date? = null,
)