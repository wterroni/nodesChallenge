package com.example.nodeschallenge.data.repository

import com.example.nodeschallenge.data.service.NodeService

class NodeRepository(private val service: NodeService) {
    suspend fun getHelloWord(): String = "Hello World"
    suspend fun getNodes() = service.getNodes()
}