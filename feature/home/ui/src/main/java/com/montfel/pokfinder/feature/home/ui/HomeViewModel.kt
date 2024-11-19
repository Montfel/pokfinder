package com.montfel.pokfinder.feature.home.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.core.common.domain.util.ResultType
import com.montfel.pokfinder.feature.home.domain.model.Generation
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import com.montfel.pokfinder.feature.home.ui.model.SortOptions
import com.montfel.pokfinder.feature.home.domain.repository.HomeRepository
import com.montfel.pokfinder.feature.home.domain.usecase.GetPokemonsUseCase
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
    private val repository: HomeRepository,
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var hasCheckedDeepLink = false

    var pokemonName by mutableStateOf("")
        private set

    private var pokemons: Flow<PagingData<PokemonHome>> = emptyFlow()

    init {
        fetchPokemons()
        loadFilters()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.SearchPokemon -> {
                updatePokemonName(event.query)
                searchPokemon(event.query)
            }

            is HomeEvent.FilterByGeneration -> filterByGeneration(event.generation)
            is HomeEvent.SortPokemonList -> sortPokemonList(event.sortOption)
            is HomeEvent.NavigateToProfile -> navigateToProfile(event.pokemonId)
            is HomeEvent.FilterByTypes -> filterByTypes(event.types)
            is HomeEvent.CheckDeepLink -> checkDeepLink(event.deepLink)
        }
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            pokemons = getPokemonsUseCase().cachedIn(viewModelScope)

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

    private fun updatePokemonName(query: String) {
        pokemonName = query
    }

    private fun searchPokemon(query: String) {
        viewModelScope.launch {
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
                        pokemonsPagingDataFlow = repository.filterPokemonsByGeneration(ids = generation.pokemonIds)
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
        viewModelScope.launch {
            val pokemonsPagingDataFlow = when (sortOption) {
                SortOptions.SmallestNumber -> pokemons
                SortOptions.HighestNumber -> {
                    repository.sortPokemonsById(orderType = sortOption.orderType)
                }

                SortOptions.Alphabetical, SortOptions.ReverseAlphabetical -> {
                    repository.sortPokemonsByName(orderType = sortOption.orderType)
                }
            }

            if (sortOption != uiState.value.sortOptionSelected) {
                _uiState.update {
                    it.copy(
                        pokemonsPagingDataFlow = pokemonsPagingDataFlow.cachedIn(viewModelScope),
                        sortOptionSelected = sortOption
                    )
                }
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

    private fun checkDeepLink(deepLink: String?) {
        if (!hasCheckedDeepLink) {
            hasCheckedDeepLink = true
            if (!deepLink.isNullOrBlank()) {
                viewModelScope.launch {
                    _uiEvent.send(HomeUiEvent.NavigateToDeepLink(deepLink))
                }
            }
        }
    }
}
