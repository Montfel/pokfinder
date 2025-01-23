package com.montfel.pokfinder.feature.profile.domain.usecase

import com.montfel.pokfinder.core.common.domain.util.ResultType
import com.montfel.pokfinder.feature.profile.domain.model.PokemonProfile
import com.montfel.pokfinder.feature.profile.domain.repository.ProfileRepository
import javax.inject.Inject

internal class GetProfileUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : GetProfileUseCase {
    override suspend fun invoke(pokemonId: Int): ResultType<PokemonProfile> {
        return repository.getProfile(pokemonId)
    }
}
