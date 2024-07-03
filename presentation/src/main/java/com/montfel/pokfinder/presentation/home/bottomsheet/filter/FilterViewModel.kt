package com.montfel.pokfinder.presentation.home.bottomsheet.filter

import androidx.lifecycle.ViewModel
import com.montfel.pokfinder.core.common.domain.model.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(FilterUiState())
    val uiState = _uiState.asStateFlow()

    fun previewSelectedTypes(selectedType: Type) {
        val selectedTypes = _uiState.value.selectedTypes.toMutableList()
        if (selectedTypes.contains(selectedType)) {
            selectedTypes.remove(selectedType)
        } else {
            selectedTypes.add(selectedType)
        }
        _uiState.update { it.copy(selectedTypes = selectedTypes) }
    }

    fun resetFilters() {
        _uiState.update {
            it.copy(selectedTypes = emptyList())
        }
    }
}
