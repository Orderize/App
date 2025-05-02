package com.orderize.orderize.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orderize.orderize.repository.profile.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<ProfileScreenUiState> = MutableStateFlow(ProfileScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                logout = { logout() }
            )
        }
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val userData = repository.getUserData()

            if (userData != null) {
                _uiState.update { currentState ->
                    currentState.copy(
                        name = userData.name,
                        role = if (userData.role != "SALOON") "Pizzaiolo" else "Atendente",
                        companyName = "Testingzzaria"
                    )
                }
            }
        }
    }

    private fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            val logout = repository.logout()
            if (logout) {
                _uiState.update { currentState ->
                    currentState.copy(userDisconnected = true)
                }
            }
        }
    }
}