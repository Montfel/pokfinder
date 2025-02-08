package com.montfel.pokfinder.core.database.converter

import androidx.room.TypeConverter
import com.montfel.pokfinder.core.database.model.dto.TypeDto
import kotlinx.serialization.json.Json

internal class Converters {
    @TypeConverter
    fun fromTypeDtoList(value: List<TypeDto>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toTypeDtoList(value: String): List<TypeDto> {
        return Json.decodeFromString(value)
    }
}
