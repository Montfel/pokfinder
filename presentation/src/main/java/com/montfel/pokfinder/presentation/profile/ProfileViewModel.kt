package com.montfel.pokfinder.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import com.montfel.pokfinder.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
            ProfileEvent.NavigateBack -> navigateBack()
            is ProfileEvent.SavePokemonId -> savePokemonId(event.pokemonId)
        }
    }

    private fun savePokemonId(pokemonId: String) {
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
            viewModelScope.launch(Dispatchers.IO) {
                val profileDeferred = async { repository.getProfile(pokemonId) }
                val speciesDeferred = async { repository.getSpecies(pokemonId) }

                val profile = profileDeferred.await()
                val species = speciesDeferred.await()

                if (profile is ResultType.Success && species is ResultType.Success) {
                    val evolutionChainDeferred = async {
                        repository.getEvolutionChain(species.data.evolutionChainId)
                    }
                    val damageRelationsDeferred = async {
                        repository.getDamageRelations(
                            profile.data.types.first().type.name.lowercase()
                        )
                    }

                    val evolutionChain = evolutionChainDeferred.await()
                    val damageRelations = damageRelationsDeferred.await()

                    if (evolutionChain is ResultType.Success && damageRelations is ResultType.Success) {
                        _uiState.update {
                            it.copy(
                                profile = profile.data,
                                species = species.data,
                                strengths = damageRelations.data.damageRelations.doubleDamageTo,
                                weaknesses = damageRelations.data.damageRelations.doubleDamageFrom,
                                immunity = damageRelations.data.damageRelations.noDamageFrom,
                                evolutionChain = evolutionChain.data,
                                stateOfUi = ProfileStateOfUi.Success
                            )
                        }
                    } else {
                        _uiState.update { it.copy(stateOfUi = ProfileStateOfUi.Error) }
                    }
                } else {
                    _uiState.update { it.copy(stateOfUi = ProfileStateOfUi.Error) }
                }
            }
        } ?: run {
            _uiState.update { it.copy(stateOfUi = ProfileStateOfUi.Error) }
        }
    }
}
