package com.example.treestructure.domain.usecases

import com.example.treestructure.domain.repository.NodesRepository
import com.example.treestructure.domain.models.Node
import javax.inject.Inject

class CreateNodeUseCase @Inject constructor(
    private val repository: NodesRepository
) {
    suspend operator fun invoke(node: Node) =
        repository.create(node)
}
