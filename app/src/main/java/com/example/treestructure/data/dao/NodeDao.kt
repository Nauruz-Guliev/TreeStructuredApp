package com.example.treestructure.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.example.treestructure.data.entity.NodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NodeDao {

    @Insert(onConflict = REPLACE)
    fun create(node: NodeEntity): Long

    @Query("SELECT * FROM node WHERE parent_id =:parentId")
    fun getNodesByParentId(parentId: Long): Flow<List<NodeEntity>>

    @Query("SELECT * FROM node WHERE id =:nodeId")
    fun getNodeById(nodeId: Long): Flow<NodeEntity>

    @Delete
    fun deleteNode(node: NodeEntity): Int

    @Query("SELECT * FROM node")
    fun getAllNodes(): Flow<List<NodeEntity>>
}