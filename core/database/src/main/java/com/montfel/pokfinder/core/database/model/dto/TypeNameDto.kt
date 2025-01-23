package com.montfel.pokfinder.core.database.model.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class TypeNameDto(
    @SerializedName("name")
    val name: String? = null,
)
