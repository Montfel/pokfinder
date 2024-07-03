package com.montfel.pokfinder.feature.home.ui

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.core.common.domain.util.ResultType
import com.montfel.pokfinder.feature.home.domain.model.Generation
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import com.montfel.pokfinder.feature.home.domain.model.SortOptions
import com.montfel.pokfinder.feature.home.repository.HomeRepository
import com.montfel.pokfinder.feature.home.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: HomeUseCases,
    private val repository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var pokemons: Flow<PagingData<PokemonHome>> = emptyFlow()

    init {
        fetchPokemons()
        loadFilters()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchPokemon -> searchPokemon(event.query)
            is HomeEvent.FilterByGeneration -> filterByGeneration(event.generation)
            is HomeEvent.SortPokemonList -> sortPokemonList(event.sortOption)
            is HomeEvent.NavigateToProfile -> navigateToProfile(event.pokemonId)
            is HomeEvent.FilterByTypes -> filterByTypes(event.types)
        }
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            pokemons = repository.getPokemons().cachedIn(viewModelScope)

            _uiState.update {
                it.copy(pokemonsPagingDataFlow = pokemons)
            }
        }
    }

    private fun navigateToProfile(pokemonId: Int) {
        viewModelScope.launch {
            _uiEvent.send(HomeUiEvent.NavigateToProfile(pokemonId))
        }
    }

    private fun loadFilters() {
        viewModelScope.launch {
            val generationsDeferred = async { repository.getGenerations() }
            val typesDeferred = async { repository.getTypes() }

            val generations = generationsDeferred.await()
            val types = typesDeferred.await()

            if (generations is ResultType.Success) {
                _uiState.update {
                    it.copy(generations = generations.data)
                }
            }

            if (types is ResultType.Success) {
                _uiState.update {
                    it.copy(types = types.data)
                }
            }
        }
    }

    private fun searchPokemon(query: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(pokemonQuery = query) }

            if (query.isNotBlank()) {
                val queryId = if (query.isDigitsOnly()) query.toInt() else 0

                _uiState.update {
                    it.copy(
                        pokemonsPagingDataFlow = repository.searchPokemons(
                            queryName = query,
                            queryId = queryId
                        ).cachedIn(viewModelScope)
                    )
                }
            } else {
                _uiState.update {
                    it.copy(pokemonsPagingDataFlow = pokemons)
                }
            }
        }
    }

    private fun filterByGeneration(generation: Generation) {
        viewModelScope.launch {
            if (generation.id != uiState.value.generationSelected) {
                _uiState.update {
                    it.copy(
                        generationSelected = generation.id,
                        pokemonsPagingDataFlow = repository.searchPokemons(queryId = generation.pokemonIds.first())
                            .cachedIn(viewModelScope)
                    )

                }
            } else {
                _uiState.update {
                    it.copy(
                        generationSelected = 0,
                        pokemonsPagingDataFlow = pokemons,
                    )
                }
            }
        }
    }

    private fun sortPokemonList(sortOption: SortOptions) {
        if (sortOption != uiState.value.sortOptionSelected) {
            val sortedPokemons =
                useCases.sortPokemonsUseCase(sortOption, uiState.value.pokemons)

            _uiState.update {
                it.copy(
                    pokemons = sortedPokemons,
                    sortOptionSelected = sortOption
                )
            }
        }
    }

    private fun filterByTypes(types: List<Type>) {
        viewModelScope.launch {
            if (types.isNotEmpty()) {
                _uiState.update {
                    it.copy(
                        pokemonsPagingDataFlow = repository.filterPokemonsByTypes(
                            types = types.mapNotNull { type -> type.name?.lowercase() }
                        ).cachedIn(viewModelScope)
                    )
                }
            } else {
                _uiState.update {
                    it.copy(pokemonsPagingDataFlow = pokemons)
                }
            }
        }
    }
}
