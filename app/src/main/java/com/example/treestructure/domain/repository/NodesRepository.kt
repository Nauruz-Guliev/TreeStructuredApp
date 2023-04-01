package com.example.treestructure.domain.repository

import com.example.treestructure.domain.models.Node
import kotlinx.coroutines.flow.Flow

interface NodesRepository {
    suspend fun create(node: Node): Long
    suspend fun getNodesByParentId(parentId: Long?): Flow<List<Node>>
    suspend fun getNodeById(nodeId: Long?): Flow<Node?>

    suspend fun deleteNode(node: Node): Int

    suspend fun deleteNode(id: Long): Int
}
