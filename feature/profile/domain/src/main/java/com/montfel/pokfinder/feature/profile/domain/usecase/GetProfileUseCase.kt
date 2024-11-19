package com.montfel.pokfinder.feature.profile.domain.usecase

import com.montfel.pokfinder.core.common.domain.util.ResultType
import com.montfel.pokfinder.feature.profile.domain.model.PokemonProfile

interface GetProfileUseCase {
    suspend operator fun invoke(pokemonId: Int): ResultType<PokemonProfile>
}