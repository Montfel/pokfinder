package com.montfel.pokfinder.data

interface DtoMapper<Domain> {
    fun toDomain(): Domain
}
