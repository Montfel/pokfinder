package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.PokemonDamageRelationsDto
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations

fun PokemonDamageRelationsDto.toDomain(): PokemonDamageRelations {
    return PokemonDamageRelations(
        damageRelations = damageRelations.toDomain()
    )
}
