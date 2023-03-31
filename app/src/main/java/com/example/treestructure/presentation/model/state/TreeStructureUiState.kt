package com.example.treestructure.presentation.model.state

sealed class TreeStructureUiState<out T> {
    object Empty: TreeStructureUiState<Nothing>()
    object Loading: TreeStructureUiState<Nothing>()
    data class Error(val message: String): TreeStructureUiState<Nothing>()
    data class Success<T>(val data: T) : TreeStructureUiState<T>()
}