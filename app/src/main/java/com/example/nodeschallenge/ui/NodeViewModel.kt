package com.example.nodeschallenge.ui

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

    private var lightningNodeList = mutableListOf<LightningNode>()

    private val _sortByCapacity = MutableStateFlow(false)
    val sortByCapacity: StateFlow<Boolean> = _sortByCapacity.asStateFlow()

    private val _sortedList = MutableStateFlow<List<LightningNode>>(emptyList())
    val sortedList: StateFlow<List<LightningNode>> = _sortedList.asStateFlow()

    init {
        fetchNodes()
        observeSortChanges()
    }

    fun fetchNodes() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val result = repository.getNodes()
                lightningNodeList = result.toMutableList()
                _state.value = UiState.Success(result)
                updateSortedList()
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message)
            }
        }
    }

    fun setSortByCapacity(value: Boolean) {
        _sortByCapacity.value = value
    }

    private fun observeSortChanges() {
        viewModelScope.launch {
            sortByCapacity.collect {
                updateSortedList()
            }
        }
    }

    private fun updateSortedList() {
        _sortedList.value = if (_sortByCapacity.value) {
            lightningNodeList.sortedBy { it.capacity }
        } else {
            lightningNodeList.sortedBy { it.channels }
        }
    }


    fun orderByCapacity(): List<LightningNode> {
        lightningNodeList.sortBy { it.capacity }
        return lightningNodeList
    }


    fun orderByChannels(): List<LightningNode> {
        lightningNodeList.sortBy { it.channels }
        return lightningNodeList
    }
}