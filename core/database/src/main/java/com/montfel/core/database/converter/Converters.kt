package com.montfel.core.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.montfel.core.database.model.dto.TypeDto

internal class Converters {
    @TypeConverter
    fun fromTypeDtoList(value: List<TypeDto>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toTypeDtoList(value: String?): List<TypeDto>? {
        val listType = object : TypeToken<List<TypeDto>>() {}.type
        return Gson().fromJson(value, listType)
    }
}