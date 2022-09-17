package com.montfel.pokedex.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    val generationSelected: Int = 0,
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

    var pokemonQuery by mutableStateOf("")
        private set

    private var pokemons = emptyList<PokemonHome>()

    init {
        loadHomePage()
    }

    fun loadHomePage() {
        viewModelScope.launch(Dispatchers.IO) {
            val pokemonListDeferred = async { repository.getPokemonList() }
            val generationListDeferred = async { repository.getGenerationList() }
            val typeListDeferred = async { repository.getTypeList() }

            val pokemonList = pokemonListDeferred.await()
            val generationList = generationListDeferred.await()
            val typeList = typeListDeferred.await()

            if (pokemonList is Response.Success &&
                generationList is Response.Success &&
                typeList is Response.Success
            ) {
                pokemons = pokemonList.data
                _uiState.update {
                    it.copy(
                        pokemonList = pokemonList.data,
                        generationList = generationList.data,
                        typeList = typeList.data,
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
        }
    }

    fun searchPokemon(query: String) {
        pokemonQuery = query

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
        if (generation.id != uiState.value.generationSelected) {
            val result = pokemons.filter { it.id in generation.pokemonId }

            _uiState.update {
                it.copy(
                    generationSelected = generation.id,
                    pokemonList = result,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    generationSelected = 0,
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
