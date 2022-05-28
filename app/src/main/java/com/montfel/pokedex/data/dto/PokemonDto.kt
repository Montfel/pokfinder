package com.montfel.pokedex.data.dto

import com.montfel.pokedex.domain.DtoMapper
import com.montfel.pokedex.domain.model.Pokemon

data class PokemonDto(
    val id: Int,
    val name: String,
//    val sprite: SpriteDto,
    val types: List<TypesDto>
) : DtoMapper<Pokemon> {
    override fun toDomain() = Pokemon(
        id = id,
        name = name.replaceFirstChar { it.uppercase() },
//        image = sprite.other.officialArtwork.frontDefault,
        types = types.map { it.toDomain() }
    )
}
