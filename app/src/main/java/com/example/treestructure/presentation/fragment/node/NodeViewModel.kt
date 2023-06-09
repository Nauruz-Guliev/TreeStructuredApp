package com.example.treestructure.presentation.fragment.node

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treestructure.R
import com.example.treestructure.presentation.utils.TextResource
import com.example.treestructure.domain.usecases.CreateNodeUseCase
import com.example.treestructure.domain.usecases.DeleteNodeByIdUseCase
import com.example.treestructure.domain.usecases.GetChildNodesUseCase
import com.example.treestructure.domain.usecases.GetNodeByIdUseCase
import com.example.treestructure.domain.models.Node
import com.example.treestructure.presentation.model.state.NodeScreenModel
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
    private val deleteNodeByIdUseCase: DeleteNodeByIdUseCase,
) : ViewModel() {

    private var _state: MutableStateFlow<TreeStructureUiState<NodeScreenModel>?> =
        MutableStateFlow(null)
    val state: StateFlow<TreeStructureUiState<NodeScreenModel>?> = _state.asStateFlow()


    suspend fun createNode(node: Node): Long {
        return createNodeUseCase(node)
    }

    fun getNodes(parentId: Long?) {
        _state.value = TreeStructureUiState.Loading
        viewModelScope.launch {
            getChildNodesUseCase(parentId).distinctUntilChanged().collect { list ->
                val parent = getNodeByIdUseCase(parentId).first()
                val model = NodeScreenModel(
                    parent = parent,
                    isRootNode = parentId == 1L,
                    childNodes = list,
                )
                _state.value = if (list.isNotEmpty()) {
                    TreeStructureUiState.Success(model)
                } else {
                    TreeStructureUiState.Empty(model)
                }
            }
        }
    }


    fun deleteNode(id: Any) {
        try {
            viewModelScope.launch {
                deleteNodeByIdUseCase(id as Long)
            }
        } catch (ex: Exception) {
            _state.value = TreeStructureUiState.Error(
                TextResource(R.string.error_node_delete)
            )
        }
    }
}
