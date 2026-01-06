package com.mgtantheta.blueprint.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mgtantheta.blueprint.core.data.repository.SampleRepository
import com.mgtantheta.blueprint.core.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val sampleRepository: SampleRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<State>(State.Loading)
        val uiState: StateFlow<State> = _uiState.asStateFlow()

        init {
            refresh()
        }

        fun refresh() =
            viewModelScope.launch {
                _uiState.value = State.Loading
                runCatching {
                    sampleRepository.refresh()
                    sampleRepository.getRepos().collect { repos ->
                        _uiState.value =
                            State.Success(
                                HomeUiState(
                                    userName = "Mgtantheta",
                                    avatarUrl = "https://github.com/Mgtantheta.png",
                                    repos = repos,
                                ),
                            )
                    }
                }.fold(
                    onSuccess = { /* collectで更新済み */ },
                    onFailure = { e -> _uiState.value = State.Error(e.message ?: "Unknown error") },
                )
            }
    }

sealed interface State {
    data object Loading : State

    data class Success(val data: HomeUiState) : State

    data class Error(val message: String) : State
}

data class HomeUiState(
    val userName: String = "Mgtantheta",
    val avatarUrl: String = "https://github.com/Mgtantheta.png",
    val repos: List<Repo> = emptyList(),
)
