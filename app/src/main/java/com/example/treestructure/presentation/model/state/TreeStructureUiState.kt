package com.example.treestructure.presentation.model.state

import com.example.treestructure.presentation.utils.TextResource

sealed class TreeStructureUiState<out T> {
    data class Empty<Parent>(val data: Parent) : TreeStructureUiState<Parent>()
    object Loading: TreeStructureUiState<Nothing>()
    data class Error(val message: TextResource): TreeStructureUiState<Nothing>()
    data class Success<T>(val data: T) : TreeStructureUiState<T>()
}
