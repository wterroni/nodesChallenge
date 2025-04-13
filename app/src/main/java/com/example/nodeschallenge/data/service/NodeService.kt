package com.example.nodeschallenge.data.service

import com.example.nodeschallenge.data.model.LightningNode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class NodeService(private val client: HttpClient) {
    suspend fun getNodes(): List<LightningNode> {
        return client.get("https://mempool.space/api/v1/lightning/nodes/rankings/connectivity").body()
    }
}