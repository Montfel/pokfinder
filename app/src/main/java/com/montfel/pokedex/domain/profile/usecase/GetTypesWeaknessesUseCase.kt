package com.montfel.pokedex.domain.profile.usecase

import com.montfel.pokedex.domain.profile.model.TypesProfile
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.ApiResponse
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class GetTypesWeaknessesUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(types: List<TypesProfile>): List<String> {
        if (types.size == 1) {
            val response = repository.getDamageRelations(types.first().type.id)
            if (response is ApiResponse.SuccessResult) {
                return response.data.damageRelations.doubleDamageFrom
            }
        } else {
            val firstType = repository.getDamageRelations(types.first().type.id)
            val secondType = repository.getDamageRelations(types.last().type.id)

            if (firstType is ApiResponse.SuccessResult && secondType is ApiResponse.SuccessResult) {
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
