package com.montfel.pokedex.presentation.profile

import androidx.lifecycle.ViewModel
import com.montfel.pokedex.domain.model.Pokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class ProfileUiState(
    val pokemon: List<Pokemon> = emptyList()
)

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
//    private val _uiState = MutableStateFlow(ProfileUiState())
//    val uiState: StateFlow<ProfileUiState> = _uiState
//
//    suspend fun getProfile(id: String) {
//        val response = withContext(Dispatchers.IO) {
//            try {
//            } catch (e: ApolloException) {
//            }
//        }
//        _uiState.update {
//            it.copy(bookDetailData = response.data)
//        }
//    }

}

