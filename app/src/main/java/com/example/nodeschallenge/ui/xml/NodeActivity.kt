package com.example.nodeschallenge.ui.xml

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nodeschallenge.R
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.databinding.ActivityNodeBinding
import com.example.nodeschallenge.ui.NodeViewModel
import com.example.nodeschallenge.ui.state.UiState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNodeBinding
    private val viewModel: NodeViewModel by viewModel()
    private lateinit var adapter: NodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSwipeRefresh()
        collectData()

        viewModel.fetchNodes()
    }

    private fun setupRecyclerView() {
        adapter = NodeAdapter(emptyList())
        binding.recyclerViewNodes.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewNodes.adapter = adapter
    }

    private fun setupSwipeRefresh() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchNodes()
        }
    }

    private fun collectData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { uiState ->
                    when (uiState) {
                        is UiState.Loading -> showLoadingState()
                        is UiState.Success -> showSuccessState(uiState.data)
                        is UiState.Error -> showErrorState(uiState.message)
                    }
                }
            }
        }
    }

    private fun showLoadingState() {
        with(binding) {
            progressBarLoading.visibility = View.VISIBLE
            recyclerViewNodes.visibility = View.GONE
            textViewError.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = true
        }
    }

    private fun showSuccessState(data: List<LightningNode>) {
        with(binding) {
            progressBarLoading.visibility = View.GONE
            recyclerViewNodes.visibility = View.VISIBLE
            textViewError.visibility = View.GONE
            swipeRefreshLayout.isRefreshing = false
            adapter.updateData(data)
        }
    }

    private fun showErrorState(message: String?) {
        with(binding) {
            progressBarLoading.visibility = View.GONE
            recyclerViewNodes.visibility = View.GONE
            textViewError.visibility = View.VISIBLE
            swipeRefreshLayout.isRefreshing = false
            textViewError.text = getString(
                R.string.activity_node_error_message_full,
                getString(R.string.activity_node_error_message),
                message ?: "Erro desconhecido"
            )
        }
    }
}