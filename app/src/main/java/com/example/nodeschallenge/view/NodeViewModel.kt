package com.example.nodeschallenge.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.data.repository.NodeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NodeViewModel(private val repository: NodeRepository) : ViewModel() {
    private val _data = MutableStateFlow("")
    val data: StateFlow<String> = _data

    private val _nodes = MutableStateFlow<List<LightningNode>>(emptyList())
    val nodes: StateFlow<List<LightningNode>> = _nodes

    fun fetchNodes() {
        viewModelScope.launch {
            try {
                val result = repository.getNodes()
                _nodes.value = result
            } catch (e: Exception) {
                Log.e("NodeResult", "Erro ao buscar nodes: ${e.message}")
            }
        }
    }
}