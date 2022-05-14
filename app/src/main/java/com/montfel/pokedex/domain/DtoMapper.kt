package com.montfel.pokedex.domain

interface DtoMapper<Domain> {
    fun toDomain(): Domain
}
