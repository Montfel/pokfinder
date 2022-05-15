package com.montfel.pokedex.presentation.home

import androidx.lifecycle.ViewModel
import com.montfel.pokedex.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class HomeUiState(
    val id: Long? = null,
    val height: Int? = null,
    val name: String? = null,
    val results: List<String> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    suspend fun getPokemon(name: String) {
        val response = pokemonRepository.getPokemon(name)
        _uiState.update {
            it.copy(
                id = response.id,
                name = response.name
            )
        }
    }

    suspend fun getAllPokemons() {
        val response = pokemonRepository.getAllPokemons()
        _uiState.update {
            it.copy(results = response.results)
        }
    }

}
