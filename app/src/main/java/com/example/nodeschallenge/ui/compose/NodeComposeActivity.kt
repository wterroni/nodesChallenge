package com.example.nodeschallenge.ui.compose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import com.example.nodeschallenge.ui.NodeViewModel
import org.koin.androidx.compose.koinViewModel

class NodeComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val viewModel: NodeViewModel = koinViewModel()

                    LaunchedEffect(Unit) {
                        viewModel.fetchNodes()
                    }

                    NodeScreen(
                        stateFlow = viewModel.state,
                        onRefresh = { viewModel.fetchNodes() }
                    )
                }
            }
        }
    }
}
