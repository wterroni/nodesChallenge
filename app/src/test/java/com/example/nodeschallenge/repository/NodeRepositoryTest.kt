package com.example.nodeschallenge.repository

import com.example.nodeschallenge.city
import com.example.nodeschallenge.country
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.data.repository.NodeRepository
import com.example.nodeschallenge.data.service.NodeService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NodeRepositoryTest {

    private lateinit var service: NodeService
    private lateinit var repository: NodeRepository

    @Before
    fun setup() {
        service = mockk()
        repository = NodeRepository(service)
    }

    @Test
    fun `getNodes should return list from service`() = runTest {

        val expected = listOf(
            LightningNode("pubkey1", "alias1", 10, 1000L, 1610000000, 1611000000, city, country)
        )

        coEvery { service.getNodes() } returns expected

        val result = repository.getNodes()

        assertEquals(expected, result)
        coVerify(exactly = 1) { service.getNodes() }
    }
}