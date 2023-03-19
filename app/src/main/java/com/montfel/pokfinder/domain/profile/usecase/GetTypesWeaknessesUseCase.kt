package com.montfel.pokfinder.domain.profile.usecase

import com.montfel.pokfinder.domain.profile.model.Types
import com.montfel.pokfinder.domain.profile.repository.ProfileRepository
import com.montfel.pokfinder.helper.Response
import javax.inject.Inject

class GetTypesWeaknessesUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(types: List<Types>): List<String> {
        if (types.size == 1) {
            val response = repository.getDamageRelations(types.first().type.name)
            if (response is Response.Success) {
                return response.data.damageRelations.doubleDamageFrom
            }
        } else {
            val firstType = repository.getDamageRelations(types.first().type.name)
            val secondType = repository.getDamageRelations(types.last().type.name)

            if (firstType is Response.Success && secondType is Response.Success) {
                val firstWeaknesses = firstType.data.damageRelations.doubleDamageFrom
                    .filter { it !in secondType.data.damageRelations.doubleDamageTo }
                val secondWeaknesses = secondType.data.damageRelations.doubleDamageFrom
                    .filter { it !in firstType.data.damageRelations.doubleDamageTo }
                val weaknesses = firstWeaknesses.union(secondWeaknesses).toMutableList()

                weaknesses.removeAll(firstType.data.damageRelations.noDamageFrom)
                weaknesses.removeAll(secondType.data.damageRelations.noDamageFrom)
                weaknesses.removeAll(types.map { it.type.name })

                return weaknesses
            }
        }
        return emptyList()
    }
}
