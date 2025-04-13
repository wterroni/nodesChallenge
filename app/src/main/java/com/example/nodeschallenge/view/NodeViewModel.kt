package com.example.nodeschallenge.view

import androidx.lifecycle.ViewModel
import com.example.nodeschallenge.data.NodeRepository

class NodeViewModel(private val repository: NodeRepository) : ViewModel() {
    fun getHelloWord(): String {
        return repository.getHelloWord()
    }
}