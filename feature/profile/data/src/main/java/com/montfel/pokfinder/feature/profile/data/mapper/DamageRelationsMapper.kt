package com.montfel.pokfinder.feature.profile.data.mapper

import com.montfel.pokfinder.feature.profile.data.model.dto.DamageRelationsDto
import com.montfel.pokfinder.domain.profile.model.DamageRelations

fun DamageRelationsDto.toDamageRelations(): DamageRelations {
    return DamageRelations(
        doubleDamageFrom = doubleDamageFrom?.mapNotNull { it.toDamageType().name }?.sorted(),
        doubleDamageTo = doubleDamageTo?.mapNotNull { it.toDamageType().name }?.sorted(),
        halfDamageFrom = halfDamageFrom?.mapNotNull { it.toDamageType().name }?.sorted(),
        halfDamageTo = halfDamageTo?.mapNotNull { it.toDamageType().name }?.sorted(),
        noDamageFrom = noDamageFrom?.mapNotNull { it.toDamageType().name }?.sorted(),
        noDamageTo = noDamageTo?.mapNotNull { it.toDamageType().name }?.sorted()
    )
}
