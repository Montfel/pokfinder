package com.montfel.pokedex.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokedex.domain.profile.model.PokemonEvolutionChain
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val pokemonProfile: PokemonProfile? = null,
    val pokemonSpecies: PokemonSpecies? = null,
    val pokemonEvolutionChain: PokemonEvolutionChain? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun getPokemonProfile(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonProfileDeferred = async { repository.getPokemonProfile(id) }
            val pokemonSpeciesDeferred = async { repository.getPokemonSpecies(id) }
            val pokemonEvolutionChainDeferred = async { repository.getPokemonEvolutionChain(id) }

            val pokemonProfile = pokemonProfileDeferred.await()
            val pokemonSpecies = pokemonSpeciesDeferred.await()
            val pokemonEvolutionChain = pokemonEvolutionChainDeferred.await()

            if (pokemonProfile is ApiResponse.SuccessResult) {
                _uiState.update { it.copy(pokemonProfile = pokemonProfile.data) }
            }
            if (pokemonSpecies is ApiResponse.SuccessResult) {
                _uiState.update { it.copy(pokemonSpecies = pokemonSpecies.data) }
            }
            if (pokemonEvolutionChain is ApiResponse.SuccessResult) {
                _uiState.update { it.copy(pokemonEvolutionChain = pokemonEvolutionChain.data) }
            }
        }
    }
}

