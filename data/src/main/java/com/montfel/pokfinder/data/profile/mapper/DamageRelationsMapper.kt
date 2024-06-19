package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.DamageRelationsDto
import com.montfel.pokfinder.domain.profile.model.DamageRelations

fun DamageRelationsDto.toDomain(): DamageRelations {
    return DamageRelations(
        doubleDamageFrom = doubleDamageFrom?.mapNotNull { it.toDomain().name }?.sorted(),
        doubleDamageTo = doubleDamageTo?.mapNotNull { it.toDomain().name }?.sorted(),
        halfDamageFrom = halfDamageFrom?.mapNotNull { it.toDomain().name }?.sorted(),
        halfDamageTo = halfDamageTo?.mapNotNull { it.toDomain().name }?.sorted(),
        noDamageFrom = noDamageFrom?.mapNotNull { it.toDomain().name }?.sorted(),
        noDamageTo = noDamageTo?.mapNotNull { it.toDomain().name }?.sorted()
    )
}
