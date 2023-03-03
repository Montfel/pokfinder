package com.montfel.pokfinder.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import com.montfel.pokfinder.helper.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState.asStateFlow()

    fun getPokemonDetails(pokemonId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val profileDeferred = async { repository.getProfile(pokemonId) }
            val speciesDeferred = async { repository.getSpecies(pokemonId) }

            val profile = profileDeferred.await()
            val species = speciesDeferred.await()

            if (profile is Response.Success && species is Response.Success) {
                val evolutionChainDeferred = async {
                    repository.getEvolutionChain(species.data.evolutionChainId)
                }
                val damageRelationsDeferred = async {
                    repository.getDamageRelations(
                        profile.data.types.first { it.slot == 1 }.type.name.lowercase()
                    )
                }

                val evolutionChain = evolutionChainDeferred.await()
                val damageRelations = damageRelationsDeferred.await()

                if (evolutionChain is Response.Success && damageRelations is Response.Success) {
                    _uiState.update {
                        it.copy(
                            profile = profile.data,
                            species = species.data,
                            strengths = damageRelations.data.damageRelations.doubleDamageTo,
                            weaknesses = damageRelations.data.damageRelations.doubleDamageFrom,
                            immunity = damageRelations.data.damageRelations.noDamageFrom,
                            evolutionChain = evolutionChain.data,
                            isLoading = false,
                            hasError = false
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            hasError = true
                        )
                    }
                }
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }
        }
    }
}
