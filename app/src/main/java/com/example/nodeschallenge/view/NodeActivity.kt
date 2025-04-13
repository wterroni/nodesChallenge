package com.example.nodeschallenge.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.nodeschallenge.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: NodeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        collectData()

        viewModel.fetchNodes()

    }

    private fun collectData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.nodes.collect { nodes ->
                    nodes.forEach { node ->
                        Log.d("NodeResult", "Node: $node")
                    }
                }
            }
        }
    }
}