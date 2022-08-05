package com.montfel.pokedex.helper

interface DtoMapper<Domain> {
    fun toDomain(): Domain
}
