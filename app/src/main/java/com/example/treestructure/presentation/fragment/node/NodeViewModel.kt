package com.example.treestructure.presentation.fragment.node

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treestructure.domain.usecases.CreateNodeUseCase
import com.example.treestructure.domain.usecases.GetChildNodesUseCase
import com.example.treestructure.domain.usecases.GetNodeByIdUseCase
import com.example.treestructure.presentation.model.Node
import com.example.treestructure.presentation.model.NodeModel
import com.example.treestructure.presentation.model.state.TreeStructureUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NodeViewModel @Inject constructor(
    private val createNodeUseCase: CreateNodeUseCase,
    private val getNodeByIdUseCase: GetNodeByIdUseCase,
    private val getChildNodesUseCase: GetChildNodesUseCase,
    ) : ViewModel() {

    private var _state: MutableStateFlow<TreeStructureUiState<NodeModel>> =
        MutableStateFlow(TreeStructureUiState.Empty)
    val state: StateFlow<TreeStructureUiState<NodeModel>> = _state.asStateFlow()


    suspend fun createNode(node: Node): Long {
        return createNodeUseCase(node)
    }

    fun getNodes(parentId: Long?) {
        viewModelScope.launch {
            getChildNodesUseCase(parentId).distinctUntilChanged().collectLatest { list ->
                val parent = getNodeByIdUseCase(parentId).first()
                _state.value = TreeStructureUiState.Success(
                    NodeModel(
                        parent = parent,
                        isRootNode = parentId == 1L,
                        childNodes = list,
                    )
                )
            }
        }
    }
}
