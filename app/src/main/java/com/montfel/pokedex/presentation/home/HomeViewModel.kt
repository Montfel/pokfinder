package com.montfel.pokedex.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokedex.domain.home.model.Generation
import com.montfel.pokedex.domain.home.model.PokemonHome
import com.montfel.pokedex.domain.home.repository.HomeRepository
import com.montfel.pokedex.domain.home.usecase.HomeUseCases
import com.montfel.pokedex.domain.profile.model.Type
import com.montfel.pokedex.helper.Response
import com.montfel.pokedex.presentation.home.bottomsheet.SortOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class HomeUiState(
    val pokemonList: List<PokemonHome> = emptyList(),
    val typeList: List<Type> = emptyList(),
    val generationList: List<Generation> = emptyList(),
    val generationSelected: String = "",
    val sortOptionSelected: SortOptions = SortOptions.SmallestNumber,
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases,
    private val repository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    private var pokemons = emptyList<PokemonHome>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonListDeferred = async { repository.getPokemonList() }
            val generationListDeferred = async { repository.getGenerationList() }
            val typeListDeferred = async { repository.getTypeList() }

            val pokemonList = pokemonListDeferred.await()
            val generationList = generationListDeferred.await()
            val typeList = typeListDeferred.await()

            if (pokemonList is Response.Success) {
                pokemons = pokemonList.data
                _uiState.update { it.copy(pokemonList = pokemonList.data) }
            }
            if (generationList is Response.Success) {
                _uiState.update { it.copy(generationList = generationList.data) }
            }
            if (typeList is Response.Success) {
                _uiState.update { it.copy(typeList = typeList.data) }
            }
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun searchPokemon(query: String) {
        if (query.isNotBlank()) {
            viewModelScope.launch(Dispatchers.Default) {
                val text = query.trim()
                val result = pokemons.filter {
                    it.name.contains(text, ignoreCase = true) || it.id.toString() == text
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
            val sortedPokemons = useCases.sortPokemonsUseCase(sortOption, uiState.value.pokemonList)

            _uiState.update {
                it.copy(
                    pokemonList = sortedPokemons,
                    sortOptionSelected = sortOption
                )
            }
        }
    }
}
