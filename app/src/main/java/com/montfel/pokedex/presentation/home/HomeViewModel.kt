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
import com.montfel.pokedex.domain.repository.PokemonRepository
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
    val pokemon: Pokemon? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

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
        _uiState.update {
            it.copy(
                pokemonList = pokemonList
            )
        }
    }

    fun searchPokemon(pokemonName: String) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    pokemon = pokemonRepository.getPokemon(pokemonName)
                )
            }
        }
    }
}
