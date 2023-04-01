package com.example.treestructure.domain.usecases

import com.example.treestructure.domain.repository.NodesRepository
import javax.inject.Inject

class GetNodeByIdUseCase @Inject constructor(
    private val repository: NodesRepository
) {
    suspend operator fun invoke(id: Long?) =
        repository.getNodeById(id)
}
