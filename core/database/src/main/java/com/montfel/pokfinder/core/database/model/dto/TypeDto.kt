package com.montfel.pokfinder.core.database.model.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TypeDto(
    @SerializedName("slot")
    val slot: Int? = null,

    @SerializedName("type")
    val typeName: TypeNameDto? = null,
)
