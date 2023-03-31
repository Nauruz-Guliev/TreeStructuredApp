package com.example.treestructure.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.treestructure.data.entity.NodeEntity
import com.example.treestructure.presentation.model.Node
import kotlinx.coroutines.flow.Flow

interface NodesRepository {

    suspend fun create(node: Node): Long

    suspend fun getNodesByParentId(parentId: Long): Flow<List<Node>>

    suspend fun getNodeById(nodeId: Long): Flow<Node>

    suspend fun deleteNode(node: Node): Int

    suspend fun getAllNodes(): Flow<List<Node>>
}