package com.example.treestructure.presentation.fragment.node

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treestructure.domain.repository.NodesRepository
import com.example.treestructure.domain.usecases.CreateNodeUseCase
import com.example.treestructure.domain.usecases.GetNodeByIdUseCase
import com.example.treestructure.presentation.model.Node
import com.example.treestructure.presentation.model.NodeModel
import com.example.treestructure.presentation.model.state.TreeStructureUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NodeViewModel @Inject constructor(
    private val createNodeUseCase: CreateNodeUseCase,
    private val getNodeByIdUseCase: GetNodeByIdUseCase,
    private val repository: NodesRepository,

) : ViewModel() {

    private var _state: MutableStateFlow<TreeStructureUiState<NodeModel>> =
        MutableStateFlow(TreeStructureUiState.Empty)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllNodes().collect {
                Log.d("ERROR", it.toString())
                _state.value = TreeStructureUiState.Success(
                    NodeModel(
                        parent = getNodeByIdUseCase(1).first(),
                        childNodes = it
                    )
                )
            }
        }
    }
    suspend fun createNode(node: Node) : Long {
        return createNodeUseCase(node)
    }
}