package com.example.nodeschallenge.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.data.repository.NodeRepository
import com.example.nodeschallenge.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NodeViewModel(private val repository: NodeRepository) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<LightningNode>>>(UiState.Loading)
    val state: StateFlow<UiState<List<LightningNode>>> = _state.asStateFlow()

    fun fetchNodes() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val result = repository.getNodes()
                _state.value = UiState.Success(result)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message)
                Log.e("NodeResult", "Erro ao buscar nodes: ${e.message}")
            }
        }
    }
}