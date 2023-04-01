package com.example.treestructure.presentation.model.state

import com.example.treestructure.domain.models.TextResource

sealed class TreeStructureUiState<out T> {
    object Empty: TreeStructureUiState<Nothing>()
    object Loading: TreeStructureUiState<Nothing>()
    data class Error(val message: TextResource): TreeStructureUiState<Nothing>()
    data class Success<T>(val data: T) : TreeStructureUiState<T>()
}
