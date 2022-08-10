package com.montfel.pokedex.data

interface DtoMapper<Domain> {
    fun toDomain(): Domain
}
