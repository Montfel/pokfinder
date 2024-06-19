package com.montfel.pokfinder.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.montfel.pokfinder.designsystem.model.AssetFromType
import com.montfel.pokfinder.domain.home.model.Generation
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.domain.home.model.SortOptions
import com.montfel.pokfinder.domain.home.repository.HomeRepository
import com.montfel.pokfinder.domain.home.usecase.HomeUseCases
import com.montfel.pokfinder.domain.util.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private var pokemons = emptyList<PokemonHome>()

    init {
        loadHomePage()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.LoadHomePage -> loadHomePage()
            is HomeEvent.SearchPokemon -> searchPokemon(event.query)
            is HomeEvent.FilterByGeneration -> filterByGeneration(event.generation)
            is HomeEvent.SortPokemonList -> sortPokemonList(event.sortOption)
            is HomeEvent.NavigateToProfile -> navigateToProfile(event.pokemonId)
            is HomeEvent.FilterByType -> filterByType(event.typeList)
            is HomeEvent.FilterByWeaknesses -> filterByWeaknesses(event.weaknessesList)
        }
    }

    private fun navigateToProfile(pokemonId: Int) {
        viewModelScope.launch {
            _uiEvent.send(HomeUiEvent.NavigateToProfile(pokemonId))
        }
    }

    private fun loadHomePage() {
        viewModelScope.launch {
            val pokemonListDeferred = async { repository.getPokemonList() }
            val generationListDeferred = async { repository.getGenerationList() }
            val typeListDeferred = async { repository.getTypeList() }

            val pokemonList = pokemonListDeferred.await()
            val generationList = generationListDeferred.await()
            val typeList = typeListDeferred.await()

            if (pokemonList is ResultType.Success &&
                generationList is ResultType.Success &&
                typeList is ResultType.Success
            ) {
                pokemons = pokemonList.data
                _uiState.update {
                    it.copy(
                        pokemons = pokemonList.data,
                        generations = generationList.data,
                        types = typeList.data,
                        stateOfUi = HomeStateOfUi.Success,
                    )
                }
            } else {
                _uiState.update { it.copy(stateOfUi = HomeStateOfUi.Error) }
            }
        }
    }

    private fun searchPokemon(query: String) {
        _uiState.update { it.copy(pokemonQuery = query) }

        if (query.isNotBlank()) {
            viewModelScope.launch(Dispatchers.Default) {
                val text = query.trim()
                val result = pokemons.filter {
                    it.name.contains(text, ignoreCase = true) || it.id.toString() == text
                }
                _uiState.update { it.copy(pokemons = result) }
            }
        } else {
            _uiState.update { it.copy(pokemons = pokemons) }
        }
    }

    private fun filterByGeneration(generation: Generation) {
        if (generation.id != uiState.value.generationSelected) {
            val result = pokemons.filter { it.id in generation.pokemonId }

            _uiState.update {
                it.copy(
                    generationSelected = generation.id,
                    pokemons = result,
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    generationSelected = 0,
                    pokemons = pokemons,
                )
            }
        }
    }

    private fun sortPokemonList(sortOption: SortOptions) {
        if (sortOption != uiState.value.sortOptionSelected) {
            val sortedPokemons = useCases.sortPokemonsUseCase(sortOption, uiState.value.pokemons)

            _uiState.update {
                it.copy(
                    pokemons = sortedPokemons,
                    sortOptionSelected = sortOption
                )
            }
        }
    }

    private fun filterByType(typeList: List<AssetFromType>) {
        if (typeList.isNotEmpty()) {
            val result = pokemons.filter {
                typeList.contains(
                    AssetFromType.getAsset(it.types.first().type?.name)
                ) ||
                        typeList.contains(
                            AssetFromType.getAsset(it.types.last().type?.name)
                        )
            }

            _uiState.update { it.copy(pokemons = result) }
        } else {
            _uiState.update { it.copy(pokemons = pokemons) }
        }
    }

    private fun filterByWeaknesses(weaknessesList: List<AssetFromType>) {
        if (weaknessesList.isNotEmpty()) {
            val result = pokemons.filter {
                weaknessesList.contains(
                    AssetFromType.getAsset(it.types.first().type?.name)
                ) || weaknessesList.contains(
                    AssetFromType.getAsset(it.types.last().type?.name)
                )
            }

            _uiState.update { it.copy(pokemons = result) }
        } else {
            _uiState.update { it.copy(pokemons = pokemons) }
        }
    }
}
