package com.montfel.pokedex.domain.profile.usecase

import com.montfel.pokedex.domain.profile.model.Types
import com.montfel.pokedex.domain.profile.repository.ProfileRepository
import com.montfel.pokedex.helper.ApiResponse
import javax.inject.Inject

class GetTypesStrengthsUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(types: List<Types>): List<String> {
        if (types.size == 1) {
            val response = repository.getDamageRelations(types.first().type.name)
            if (response is ApiResponse.SuccessResult) {
                return response.data.damageRelations.doubleDamageTo
            }
        } else {
            val firstType = repository.getDamageRelations(types.first().type.name)
            val secondType = repository.getDamageRelations(types.last().type.name)

            if (firstType is ApiResponse.SuccessResult && secondType is ApiResponse.SuccessResult) {
                val firstStrengths = firstType.data.damageRelations.doubleDamageTo
                    .filter { it !in secondType.data.damageRelations.doubleDamageFrom }
                val secondStrengths = secondType.data.damageRelations.doubleDamageTo
                    .filter { it !in firstType.data.damageRelations.doubleDamageFrom }
                val strengths = firstStrengths.union(secondStrengths).toMutableList()

                strengths.removeAll(firstType.data.damageRelations.noDamageTo)
                strengths.removeAll(secondType.data.damageRelations.noDamageTo)
                strengths.removeAll(types.map { it.type.name })

                return strengths
            }
        }
        return emptyList()
    }
}
