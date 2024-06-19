package com.montfel.pokfinder.presentation.home.bottomsheet.filter

import androidx.lifecycle.ViewModel
import com.montfel.pokfinder.designsystem.model.AssetFromType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FilterUiState())
    val uiState = _uiState.asStateFlow()

    fun previewSelectedTypes(selectedType: AssetFromType) {
        val selectedTypes = _uiState.value.selectedTypes.toMutableList()
        if (selectedTypes.contains(selectedType)) {
            selectedTypes.remove(selectedType)
        } else {
            selectedTypes.add(selectedType)
        }
        _uiState.update { it.copy(selectedTypes = selectedTypes) }
    }

    fun previewSelectedWeaknesses(selectedWeakness: AssetFromType) {
        val selectedWeaknesses = _uiState.value.selectedWeaknesses.toMutableList()
        if (selectedWeaknesses.contains(selectedWeakness)) {
            selectedWeaknesses.remove(selectedWeakness)
        } else {
            selectedWeaknesses.add(selectedWeakness)
        }
        _uiState.update { it.copy(selectedWeaknesses = selectedWeaknesses) }
    }

    fun previewSelectedHeights(selectedHeight: AssetFromType) {
        val selectedHeights = _uiState.value.selectedHeights.toMutableList()
        if (selectedHeights.contains(selectedHeight)) {
            selectedHeights.remove(selectedHeight)
        } else {
            selectedHeights.add(selectedHeight)
        }
        _uiState.update { it.copy(selectedHeights = selectedHeights) }
    }

    fun previewSelectedWeights(selectedWeight: AssetFromType) {
        val selectedWeights = _uiState.value.selectedWeights.toMutableList()
        if (selectedWeights.contains(selectedWeight)) {
            selectedWeights.remove(selectedWeight)
        } else {
            selectedWeights.add(selectedWeight)
        }
        _uiState.update { it.copy(selectedWeights = selectedWeights) }
    }

    fun resetFilters() {
        _uiState.update {
            it.copy(
                selectedTypes = emptyList(),
                selectedWeaknesses = emptyList(),
                selectedHeights = emptyList(),
                selectedWeights = emptyList(),
            )
        }
    }
}
