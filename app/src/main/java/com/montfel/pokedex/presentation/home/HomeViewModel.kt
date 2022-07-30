package com.montfel.pokedex.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.montfel.pokedex.GenerationsQuery
import com.montfel.pokedex.ListQuery
import com.montfel.pokedex.TypesQuery
import com.montfel.pokedex.data.datasource.apolloClient
import com.montfel.pokedex.data.dto.GenerationDto
import com.montfel.pokedex.data.dto.PokemonDto
import com.montfel.pokedex.data.dto.TypeDto
import com.montfel.pokedex.data.dto.TypesDto
import com.montfel.pokedex.domain.model.Generation
import com.montfel.pokedex.domain.model.PokemonHome
import com.montfel.pokedex.domain.usecase.HomeUseCases
import com.montfel.pokedex.helper.Asset
import com.montfel.pokedex.helper.AssetHelper
import com.montfel.pokedex.presentation.home.bottomsheet.SortOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class HomeUiState(
    val pokemonList: List<PokemonHome> = emptyList(),
    val assetList: List<Asset> = emptyList(),
    val generationList: List<Generation> = emptyList(),
    val generationSelected: String = "",
    val sortOptionSelected: SortOptions = SortOptions.SmallestNumber,
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private var pokemons = emptyList<PokemonHome>()

    suspend fun showAllPokemons() {
        val response = withContext(Dispatchers.IO) {
            try {
                apolloClient.query(ListQuery()).execute()
            } catch (e: ApolloException) {
                throw Exception()
            }
        }

        response.data?.let { data ->
            val pokemonList = data.pokemon_v2_pokemon.map {
                PokemonDto(
                    id = it.id,
                    name = it.name,
                    types = it.pokemon_v2_pokemontypes.map { type ->
                        TypesDto(
                            slot = type.slot,
                            type = TypeDto(
                                name = type.pokemon_v2_type?.name
                            )
                        )
                    }
                ).toDomain()
            }
            pokemons = pokemonList
            _uiState.update { it.copy(pokemonList = pokemonList) }
        }
    }

    suspend fun saveAllTypes(assetHelper: AssetHelper) {
        val response = withContext(Dispatchers.IO) {
            try {
                apolloClient.query(TypesQuery()).execute()
            } catch (e: ApolloException) {
                throw Exception()
            }
        }

        response.data?.let { data ->
            val typesList = data.pokemon_v2_type
                .map { TypeDto(name = it.name).toDomain() }
                .filter { it.name != "Unknown" && it.name != "Shadow" }
                .sortedBy { it.name }
                .map { assetHelper.getAsset(it.name) }

            _uiState.update { it.copy(assetList = typesList) }
        }
    }

    suspend fun saveAllGenerations() {
        val response = withContext(Dispatchers.IO) {
            try {
                apolloClient.query(GenerationsQuery()).execute()
            } catch (e: ApolloException) {
                throw Exception()
            }
        }

        response.data?.let { data ->
            val generationList = data.pokemon_v2_generation.map {
                GenerationDto(
                    name = it.name,
                    id = it.pokemon_v2_pokemonspecies.map { pokemonId ->
                        pokemonId.id
                    }
                ).toDomain()
            }

            _uiState.update { it.copy(generationList = generationList) }
        }
    }

    fun searchPokemon(query: String) {
        if (query.isNotBlank()) {
            viewModelScope.launch(Dispatchers.Default) {
                val result = pokemons.filter {
                    it.name.contains(query.trim(), ignoreCase = true)
                            || it.id.toString() == query.trim()
                }
                _uiState.update { it.copy(pokemonList = result) }
            }
        } else {
            _uiState.update { it.copy(pokemonList = pokemons) }
        }
    }

    fun filterByGeneration(generation: Generation) {
        if (generation.name != uiState.value.generationSelected) {
            val result = pokemons.filter { it.id in generation.id }

            _uiState.update {
                it.copy(
                    generationSelected = generation.name,
                    pokemonList = result,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    generationSelected = "",
                    pokemonList = pokemons,
                )
            }
        }
    }

    fun sortPokemons(sortOption: SortOptions) {
        if (sortOption != uiState.value.sortOptionSelected) {
            val sortedPokemons =
                homeUseCases.sortPokemonsUseCase(sortOption, uiState.value.pokemonList)

            _uiState.update {
                it.copy(
                    pokemonList = sortedPokemons,
                    sortOptionSelected = sortOption
                )
            }
        }
    }
}
