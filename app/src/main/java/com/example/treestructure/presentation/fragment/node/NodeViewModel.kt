package com.example.treestructure.presentation.fragment.node

import androidx.lifecycle.ViewModel
import com.example.treestructure.domain.usecases.CreateNodeUseCase
import com.example.treestructure.presentation.model.Node
import com.example.treestructure.presentation.model.state.TreeStructureUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NodeViewModel @Inject constructor(
    private val createNodeUseCase: CreateNodeUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<TreeStructureUiState<Node>> = MutableStateFlow(TreeStructureUiState.Empty)
    val state = _state.asStateFlow()

}