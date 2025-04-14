package com.example.nodeschallenge.ui

import com.example.nodeschallenge.city
import com.example.nodeschallenge.country
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.data.repository.NodeRepository
import com.example.nodeschallenge.ui.state.UiState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class NodeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var repository: NodeRepository
    private lateinit var viewModel: NodeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        repository = mockk()
        viewModel = NodeViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchNodes emits loading and success`() = runTest {

        val fakeNodes = listOf(
            LightningNode("pubkey1", "ACINQ", 2297, 100000, 1620000000, 1621000000, city, country)
        )
        coEvery { repository.getNodes() } returns fakeNodes

        val results = mutableListOf<UiState<List<LightningNode>>>()

        val job = launch {
            viewModel.state.toList(results)
        }

        viewModel.fetchNodes()
        advanceUntilIdle()

        assert(results[0] is UiState.Loading)
        assert(results[1] is UiState.Success)
        assertEquals(fakeNodes, (results[1] as UiState.Success).data)

        job.cancel()
    }

    @Test
    fun `fetchNodes emits error on exception`() = runTest {
        coEvery { repository.getNodes() } throws RuntimeException("Network Error")

        val results = mutableListOf<UiState<List<LightningNode>>>()

        val job = launch {
            viewModel.state.toList(results)
        }

        viewModel.fetchNodes()
        advanceUntilIdle()

        assert(results[0] is UiState.Loading)
        assert(results[1] is UiState.Error)
        assertEquals("Network Error", (results[1] as UiState.Error).message)

        job.cancel()
    }
}