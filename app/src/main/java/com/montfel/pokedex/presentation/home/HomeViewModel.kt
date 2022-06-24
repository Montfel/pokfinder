package com.montfel.pokedex.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.montfel.pokedex.ListQuery
import com.montfel.pokedex.data.datasource.apolloClient
import com.montfel.pokedex.data.dto.PokemonDto
import com.montfel.pokedex.data.dto.TypeDto
import com.montfel.pokedex.data.dto.TypesDto
import com.montfel.pokedex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class HomeUiState(
    val pokemonList: List<Pokemon>? = emptyList(),
)

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private var pokemons = emptyList<Pokemon>()

    suspend fun showAllPokemons() {
        val response = withContext(Dispatchers.IO) {
            try {
                apolloClient.query(ListQuery()).execute()
            } catch (e: ApolloException) {
                throw Exception()
            }
        }
        val pokemonList = response.data?.pokemon_v2_pokemon?.map {
            PokemonDto(
                id = it.id,
                name = it.name,
                types = it.pokemon_v2_pokemontypes.map { type ->
                    TypesDto(
                        slot = type.slot,
                        type = TypeDto(
                            name = type.pokemon_v2_type?.name ?: ""
                        )
                    )
                }
            ).toDomain()
        }
        pokemons = pokemonList ?: emptyList()
        _uiState.update { it.copy(pokemonList = pokemonList) }
    }

    fun searchPokemon(query: String) {
        if (query.isNotBlank()) {
            viewModelScope.launch(Dispatchers.Default) {
                val result = pokemons
                    .filter {
                        it.name?.contains(query.trim(), ignoreCase = true) ?: false
                                || it.id.toString() == query.trim()
                    }
                _uiState.update { it.copy(pokemonList = result) }
            }
        } else {
            _uiState.update { it.copy(pokemonList = pokemons) }
        }
    }
}
