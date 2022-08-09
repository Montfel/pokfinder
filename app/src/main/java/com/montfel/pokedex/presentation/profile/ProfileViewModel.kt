package com.montfel.pokedex.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokedex.domain.profile.model.*
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
    val profile: PokemonProfile? = null,
    val species: PokemonSpecies? = null,
//    val evolutionChain: PokemonEvolutionChain? = null,
    val strengths: List<String> = emptyList(),
    val weaknesses: List<String> = emptyList(),
    val immunity: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun getPokemonDetails(pokemonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val profileDeferred = async { repository.getProfile(pokemonId) }
            val speciesDeferred = async { repository.getSpecies(pokemonId) }
//            val evolutionChainDeferred = async { repository.getEvolutionChain(pokemonId) }

            val profile = profileDeferred.await()
            val species = speciesDeferred.await()
//            val evolutionChain = evolutionChainDeferred.await()

            if (profile is ApiResponse.SuccessResult) {
                getDamageRelations(profile.data.types)
                _uiState.update { it.copy(profile = profile.data) }
            }
            if (species is ApiResponse.SuccessResult) {
                _uiState.update { it.copy(species = species.data) }
            }
//            if (evolutionChain is ApiResponse.SuccessResult) {
//                _uiState.update { it.copy(evolutionChain = evolutionChain.data) }
//            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun getDamageRelations(types: List<TypesProfile>) {
        val response = repository.getDamageRelations(types.first().type.id)
        if (response is ApiResponse.SuccessResult) {
            _uiState.update {
                it.copy(
                    strengths = response.data.damageRelations.doubleDamageTo,
                    weaknesses = response.data.damageRelations.doubleDamageFrom,
                    immunity = response.data.damageRelations.noDamageFrom,
                )
            }
        }
    }
}
