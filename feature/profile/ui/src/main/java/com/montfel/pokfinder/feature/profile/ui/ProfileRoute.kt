package com.montfel.pokfinder.feature.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.pokfinder.core.designsystem.components.ErrorScreen
import com.montfel.pokfinder.core.designsystem.components.LoadingScreen

@Composable
fun ProfileRoute(
    id: Int,
    onNavigateToProfile: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(ProfileEvent.SavePokemonId(id))
        viewModel.onEvent(ProfileEvent.FetchPokemonDetails)
        viewModel.uiEvent.collect { event ->
            when (event) {
                is ProfileUiEvent.NavigateBack -> onNavigateBack()
                is ProfileUiEvent.NavigateToProfile -> onNavigateToProfile(event.pokemonId)
            }
        }
    }

    when (uiState.stateOfUi) {
        ProfileStateOfUi.Error -> {
            ErrorScreen(onClick = { viewModel.onEvent(ProfileEvent.FetchPokemonDetails) })
        }

        ProfileStateOfUi.Loading -> {
            LoadingScreen()
        }

        ProfileStateOfUi.Success -> {
            ProfileScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        }
    }
}
