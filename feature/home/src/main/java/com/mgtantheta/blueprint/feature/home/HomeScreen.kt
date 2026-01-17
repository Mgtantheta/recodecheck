package com.mgtantheta.blueprint.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mgtantheta.blueprint.core.model.Repo

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onClick = viewModel::refresh,
        modifier = modifier,
    )
}

@Composable
private fun HomeScreen(
    state: State,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        when (state) {
            is State.Loading -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is State.Success -> {
                HomeContent(
                    uiState = state.data,
                    modifier = Modifier.padding(innerPadding),
                )
            }

            is State.Error -> {
                Box(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(text = state.message)
                        Button(onClick = onClick) {
                            Text(text = "Retry")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
            AsyncImage(
                model = uiState.avatarUrl,
                contentDescription = "GitHub Avatar",
                modifier =
                    Modifier
                        .size(120.dp)
                        .clip(CircleShape),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = uiState.userName,
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Repositories",
                style = MaterialTheme.typography.titleMedium,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        items(uiState.repos) { repo ->
            Card(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp),
            ) {
                Text(
                    text = repo.name,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private class StatePreviewParameterProvider : PreviewParameterProvider<State> {
    override val values =
        sequenceOf(
            State.Loading,
            State.Success(HomeUiState()),
            State.Success(
                HomeUiState(
                    userName = "Android",
                    avatarUrl = "",
                    repos =
                        listOf(
                            Repo(name = "android-app"),
                            Repo(name = "kotlin-library"),
                            Repo(name = "compose-samples"),
                        ),
                ),
            ),
            State.Error("Something went wrong"),
        )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(StatePreviewParameterProvider::class) state: State,
) {
    HomeScreen(state = state, onClick = {})
}
