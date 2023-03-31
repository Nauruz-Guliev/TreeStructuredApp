package com.example.treestructure.presentation.model


import com.example.treestructure.data.utils.converter.DateConverter
import java.util.*

data class Node(
    val id: Long = 0,
    val name: String = "",
    val level: Int = 0,
    val parentId: Long? = 0,
    val createdAt: Date? = null,
)
