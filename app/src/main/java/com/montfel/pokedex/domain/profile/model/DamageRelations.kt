package com.montfel.pokedex.domain.profile.model

data class DamageRelations(
    val doubleDamageFrom: List<String>,
    val doubleDamageTo: List<String>,
    val halfDamageFrom: List<String>,
    val halfDamageTo: List<String>,
    val noDamageFrom: List<String>,
    val noDamageTo: List<String>
)
