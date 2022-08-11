package com.montfel.pokedex.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokedex.domain.profile.model.PokemonProfile
import com.montfel.pokedex.domain.profile.model.PokemonSpecies
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.Response
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

            val profile = profileDeferred.await()
            val species = speciesDeferred.await()

            if (profile is Response.Success && species is Response.Success) {
                val damageRelations =
                    repository.getDamageRelations(profile.data.types.first { it.slot == 1 }.type.name.lowercase())
                if (damageRelations is Response.Success) {
                    _uiState.update {
                        it.copy(
                            profile = profile.data,
                            species = species.data,
                            strengths = damageRelations.data.damageRelations.doubleDamageTo,
                            weaknesses = damageRelations.data.damageRelations.doubleDamageFrom,
                            immunity = damageRelations.data.damageRelations.noDamageFrom,
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
