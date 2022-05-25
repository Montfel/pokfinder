package com.montfel.pokedex.presentation.profile

import androidx.lifecycle.ViewModel
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ProfileUiState(
    val pokemon: Pokemon? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    suspend fun getProfile(id: String) {
        val response = pokemonRepository.getPokemonProfile(id)
        _uiState.update {
            it.copy(pokemon = response)
        }
    }
}

