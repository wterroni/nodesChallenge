package com.example.nodeschallenge.view

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

    fun getHelloWord() {
        viewModelScope.launch {
            val result = repository.getHelloWord()
            _data.value = result
        }
    }

    fun fetchNodes() {
        viewModelScope.launch {
            _nodes.value = repository.getNodes()
        }
    }
}