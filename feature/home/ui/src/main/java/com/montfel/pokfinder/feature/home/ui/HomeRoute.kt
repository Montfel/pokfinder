package com.montfel.pokfinder.feature.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeRoute(
    deepLink: String?,
    onNavigateToProfile: (id: Int) -> Unit,
    onNavigateToDeepLink: (deepLink: String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val pokemonName = viewModel.pokemonName
    val pokemonLazyPagingItems = uiState.pokemonsPagingDataFlow.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(HomeEvent.CheckDeepLink(deepLink))

        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUiEvent.NavigateToProfile -> onNavigateToProfile(event.pokemonId)
                is HomeUiEvent.NavigateToDeepLink -> onNavigateToDeepLink(event.deepLink)
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        pokemonName = pokemonName,
        pokemonLazyPagingItems = pokemonLazyPagingItems,
        onEvent = viewModel::onEvent
    )
}
