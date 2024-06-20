package com.montfel.pokfinder.data.mapper

import com.montfel.pokfinder.data.dto.profile.PokemonDamageRelationsDto
import com.montfel.pokfinder.domain.profile.model.PokemonDamageRelations

fun PokemonDamageRelationsDto.toDomain(): PokemonDamageRelations {
    return PokemonDamageRelations(
        damageRelations = damageRelations?.toDomain()
    )
}
