package com.montfel.pokfinder.data.profile.mapper

import com.montfel.pokfinder.data.profile.dto.DamageRelationsDto
import com.montfel.pokfinder.domain.profile.model.DamageRelations

fun DamageRelationsDto.toDomain(): DamageRelations {
    return DamageRelations(
        doubleDamageFrom = doubleDamageFrom.map { it.toDomain().name }.sorted(),
        doubleDamageTo = doubleDamageTo.map { it.toDomain().name }.sorted(),
        halfDamageFrom = halfDamageFrom.map { it.toDomain().name }.sorted(),
        halfDamageTo = halfDamageTo.map { it.toDomain().name }.sorted(),
        noDamageFrom = noDamageFrom.map { it.toDomain().name }.sorted(),
        noDamageTo = noDamageTo.map { it.toDomain().name }.sorted()
    )
}
