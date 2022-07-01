package com.montfel.pokedex.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.montfel.pokedex.ProfileQuery
import com.montfel.pokedex.data.datasource.apolloClient
import com.montfel.pokedex.data.dto.*
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.domain.repository.PokemonRepository
import com.montfel.pokedex.helper.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.floor

data class ProfileUiState(
    val pokemon: Pokemon? = null,
    val pokemonHeader: Pokemon? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun getPokemonHeader(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = pokemonRepository.getPokemon(id)

            if (response is ApiResponse.SuccessResult) {
                _uiState.update {
                    it.copy(
                        pokemonHeader = response.data
                    )
                }
            }
        }
    }

    suspend fun getProfile(id: String) {
        val response = withContext(Dispatchers.IO) {
            try {
                apolloClient.query(ProfileQuery(id.toInt())).execute()
            } catch (e: ApolloException) {
                throw Exception()
            }
        }
        val pokemonResponse = response.data?.pokemon_v2_pokemon_by_pk
        val pokemon = pokemonResponse?.let {
            val species = it.pokemon_v2_pokemonspecy
            PokemonProfileDto(
                id = it.id,
                name = it.name,
                types = it.pokemon_v2_pokemontypes.map { type ->
                    TypesDto(
                        slot = type.slot,
                        type =
                        TypeDto(
                            name = type.pokemon_v2_type?.name,
                            typeEfficacies = type.pokemon_v2_type?.pokemon_v2_typeefficacies?.map { typeEfficacy ->
                                TypeEfficacyDto(
                                    damageFactor = typeEfficacy.damage_factor,
                                    name = typeEfficacy.pokemonV2TypeByTargetTypeId?.name
                                )
                            }
                        )
                    )
                },
                height = it.height,
                weight = it.weight,
                baseExp = it.base_experience,
                abilities = it.pokemon_v2_pokemonabilities.map { ability ->
                    AbilitiesDto(
                        isHidden = ability.is_hidden,
                        slot = ability.slot,
                        name = ability.pokemon_v2_ability?.name
                    )
                },
                stats = it.pokemon_v2_pokemonstats.map { stat ->
                    StatsDto(
                        baseStat = stat.base_stat,
                        effort = stat.effort,
                        name = when (stat.pokemon_v2_stat?.name) {
                            "hp" -> "HP"
                            "attack" -> "Attack"
                            "defense" -> "Defense"
                            "special-attack" -> "Sp. Atk"
                            "special-defense" -> "Sp. Def"
                            "speed" -> "Speed"
                            else -> "Unknown"
                        },
                        min = if (stat.pokemon_v2_stat?.name == "hp") stat.base_stat * 2 + 110
                        else stat.base_stat.times(2),
                        max = if (stat.pokemon_v2_stat?.name == "hp") stat.base_stat * 2 + 204
                        else floor(stat.base_stat.times(2.2).plus(108.9)).toInt()
                    )
                },
                captureRate = species?.capture_rate,
                genderRate = species?.gender_rate,
                hatchCounter = species?.hatch_counter,
                growthRate = species?.pokemon_v2_growthrate?.name
                    ?.split("-")
                    ?.joinToString(separator = " ") { word ->
                        word.replaceFirstChar { letter ->
                            letter.uppercase()
                        }
                    },
                eggGroups = species?.pokemon_v2_pokemonegggroups?.map { egg ->
                    egg.pokemon_v2_egggroup?.name
                },
                flavorTexts = species?.pokemon_v2_pokemonspeciesflavortexts?.map { flavor ->
                    FlavorTextDto(
                        flavorText = flavor.flavor_text,
                        language = LanguageDto(
                            name = flavor.pokemon_v2_language?.name ?: ""
                        )
                    )
                },
                evolutionChain = species?.pokemon_v2_evolutionchain?.pokemon_v2_pokemonspecies?.map { specie ->
                    SpeciesDto(
                        id = specie.id,
                        name = specie.name,
                        minLevel = specie.pokemon_v2_pokemonevolutions.map { level -> level.min_level }
                    )
                },
                baseHappiness = species?.base_happiness,
                genera = species?.pokemon_v2_pokemonspeciesnames?.map { genera ->
                    GeneraDto(
                        name = genera.genus,
                        language = LanguageDto(
                            name = genera.pokemon_v2_language?.name ?: ""
                        )
                    )
                }
            ).toDomain()
        }

        _uiState.update {
            it.copy(
                pokemon = pokemon
            )
        }
    }
}

