package com.montfel.pokedex.domain.profile.model

import com.montfel.pokedex.helper.AssetFromType

data class TypeProfile(
    val name: String,
    val id: String,
    val assetFromType: AssetFromType
)
