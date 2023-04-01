package com.example.treestructure.presentation.fragment.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.treestructure.domain.usecases.GetNodeByIdUseCase
import com.example.treestructure.domain.util.Constants
import com.example.treestructure.domain.models.Node
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RootNodeViewModel @Inject constructor(
    private val getNodeByIdUseCase: GetNodeByIdUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<Node?> = MutableStateFlow(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getNodeByIdUseCase.invoke(Constants.ROOT_NODE_ID).collectLatest { node ->
                _state.value = node
            }
        }
    }
}
