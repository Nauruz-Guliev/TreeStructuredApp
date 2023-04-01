package com.example.treestructure.domain.usecases

import com.example.treestructure.domain.repository.NodesRepository
import javax.inject.Inject


class GetChildNodesUseCase @Inject constructor(
    private val repository: NodesRepository
) {
    suspend operator fun invoke(parentId: Long?) =
        repository.getNodesByParentId(parentId)
}
