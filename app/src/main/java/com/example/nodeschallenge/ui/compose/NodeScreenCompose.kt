package com.example.nodeschallenge.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.nodeschallenge.data.model.LightningNode
import com.example.nodeschallenge.ui.state.UiState
import com.example.nodeschallenge.utils.toBTC
import com.example.nodeschallenge.utils.toFormattedDate
import kotlinx.coroutines.flow.StateFlow

@Composable
fun NodeScreen(
    stateFlow: StateFlow<UiState<List<LightningNode>>>,
    onRefresh: () -> Unit
) {
    val state by stateFlow.collectAsStateWithLifecycle()

    when (state) {
        is UiState.Loading -> LoadingView()
        is UiState.Success -> NodeList((state as UiState.Success<List<LightningNode>>).data)
        is UiState.Error -> ErrorView((state as UiState.Error).message, onRefresh)
    }
}

@Composable
fun NodeList(nodes: List<LightningNode>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(nodes) { node ->
            NodeCard(node)
        }
    }
}

@Composable
fun NodeCard(node: LightningNode) {
    val city = node.city?.ptBR ?: node.city?.en ?: ""
    val country = node.country?.ptBR ?: node.country?.en ?: ""

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = node.alias, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
            Text(text = node.publicKey, style = MaterialTheme.typography.bodySmall)
            Text(text = "Channels: ${node.channels}")
            Text(text = "Capacity: ${node.capacity.toBTC()} BTC")
            Text(text = "First Seen: ${node.firstSeen.toFormattedDate()}")
            Text(text = "Last Updated: ${node.updatedAt.toFormattedDate()}")
            Text(text = "Location: $city, $country")
        }
    }
}

@Composable
fun LoadingView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView(message: String?, onRetry: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message ?: "Unknown error", color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onRetry) {
                Text("Try Again")
            }
        }
    }
}