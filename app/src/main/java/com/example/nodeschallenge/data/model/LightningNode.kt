package com.example.nodeschallenge.data.model

data class LightningNode(
    val publicKey: String,
    val alias: String,
    val channels: Int,
    val capacity: Long,
    val firstSeen: Long,
    val updatedAt: Long,
    val city: LocalizedText? = null,
    val country: LocalizedText?
)