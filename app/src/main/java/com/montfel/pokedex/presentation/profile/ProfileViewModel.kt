package com.montfel.pokedex.presentation.profile

import androidx.lifecycle.ViewModel
import com.apollographql.apollo3.exception.ApolloException
import com.montfel.pokedex.ProfileQuery
import com.montfel.pokedex.data.datasource.apolloClient
import com.montfel.pokedex.data.dto.*
import com.montfel.pokedex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class ProfileUiState(
    val pokemon: Pokemon? = null
)

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

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
                        name = type.pokemon_v2_type?.name
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
                        name = stat.pokemon_v2_stat?.name
                    )
                },
                captureRate = species?.capture_rate,
                genderRate = species?.gender_rate,
                hatchCounter = species?.hatch_counter,
                growthRate = species?.pokemon_v2_growthrate?.name,
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

