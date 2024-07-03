package com.montfel.pokfinder.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokfinder.feature.profile.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<ProfileUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.FetchPokemonDetails -> fetchPokemonDetails()
            is ProfileEvent.NavigateToProfile -> navigateToProfile(event.pokemonId)
            is ProfileEvent.NavigateBack -> navigateBack()
            is ProfileEvent.SavePokemonId -> savePokemonId(event.pokemonId)
        }
    }

    private fun savePokemonId(pokemonId: Int) {
        _uiState.update { it.copy(pokemonId = pokemonId) }
    }

    private fun navigateToProfile(pokemonId: Int) {
        viewModelScope.launch {
            _uiEvent.send(ProfileUiEvent.NavigateToProfile(pokemonId))
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _uiEvent.send(ProfileUiEvent.NavigateBack)
        }
    }

    private fun fetchPokemonDetails() {
        _uiState.value.pokemonId?.let { pokemonId ->
            viewModelScope.launch {
                val profileDeferred = async { repository.getProfile(pokemonId) }
                val speciesDeferred = async { repository.getSpecies(pokemonId) }

                val profile = profileDeferred.await()
                val species = speciesDeferred.await()

                if (profile is com.montfel.pokfinder.core.common.domain.util.ResultType.Success) {
                    _uiState.update {
                        it.copy(
                            profile = profile.data,
                            stateOfUi = ProfileStateOfUi.Success
                        )
                    }

                    if (species is com.montfel.pokfinder.core.common.domain.util.ResultType.Success) {
                        _uiState.update {
                            it.copy(
                                species = species.data,
                                stateOfUi = ProfileStateOfUi.Success
                            )
                        }

                        val evolutionChain = species.data.evolutionChainId?.let {
                            repository.getEvolutionChain(it)
                        }

                        if (evolutionChain is com.montfel.pokfinder.core.common.domain.util.ResultType.Success) {
                            _uiState.update { it.copy(evolutionChain = evolutionChain.data) }
                        }
                    }

                    val damageRelations =
                        profile.data.types?.firstOrNull()?.name?.lowercase()?.let {
                            repository.getDamageRelations(it)
                        }

                    if (damageRelations is com.montfel.pokfinder.core.common.domain.util.ResultType.Success) {
                        _uiState.update {
                            it.copy(
                                strengths = damageRelations.data.damageRelations?.doubleDamageTo,
                                weaknesses = damageRelations.data.damageRelations?.doubleDamageFrom,
                                immunity = damageRelations.data.damageRelations?.noDamageFrom,
                            )
                        }
                    }
                } else {
                    _uiState.update { it.copy(stateOfUi = ProfileStateOfUi.Error) }
                }
            }
        }
    }
}
