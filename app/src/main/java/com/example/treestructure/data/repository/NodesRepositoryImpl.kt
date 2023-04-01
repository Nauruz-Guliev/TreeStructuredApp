package com.example.treestructure.data.repository

import com.example.treestructure.data.dao.NodeDao
import com.example.treestructure.data.mapper.NodeMapper
import com.example.treestructure.domain.repository.NodesRepository
import com.example.treestructure.presentation.model.Node
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NodesRepositoryImpl
@Inject constructor(
    private val nodeDao: NodeDao,
    private val mapper: NodeMapper
) : NodesRepository {

    private val defaultDispatcher = Dispatchers.IO

    override suspend fun create(node: Node): Long =
        withContext(defaultDispatcher) {
            nodeDao.create(
                mapper.convertToEntity(node)
            )
        }


    override suspend fun getNodesByParentId(parentId: Long?): Flow<List<Node>> =
        withContext(defaultDispatcher) {
            nodeDao.getNodesByParentId(
                parentId
            ).map { nodesList ->
                nodesList.map { entity ->
                    mapper.convertToModel(entity)
                }
            }
        }


    override suspend fun getNodeById(nodeId: Long?): Flow<Node> =
        withContext(defaultDispatcher) {
            nodeDao.getNodeById(
                nodeId
            ).map { entity ->
                mapper.convertToModel(entity)
            }
        }

    override suspend fun deleteNode(node: Node): Int =
        withContext(defaultDispatcher) {
            nodeDao.deleteNode(
                mapper.convertToEntity(node)
            )
        }

    @Deprecated(message = "Этот метод экспериментальный. Использовать не советуется")
    override suspend fun getAllNodes(): Flow<List<Node>> =
        withContext(defaultDispatcher) {
            nodeDao.getAllNodes().map { nodeList ->
                nodeList.map { entity ->
                    mapper.convertToModel(entity)
                }
            }
        }


}
