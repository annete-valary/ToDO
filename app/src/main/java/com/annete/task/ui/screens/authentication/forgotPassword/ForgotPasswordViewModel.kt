package com.annete.task.ui.screens.authentication.forgotPassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.annete.task.data.models.ForgotPasswordUiState
import com.annete.task.data.repositories.AuthRepository
import com.annete.task.data.repositories.AuthRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import com.annete.task.data.util.toUserFriendlyMessage

class ForgotPasswordViewModel(
    private val repository: AuthRepository = AuthRepositoryImpl()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, errorMessage = null) }
    }

    fun resetPassword() {
        val email = uiState.value.email

        if (email.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Please enter your email") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = repository.resetPassword(email)
            _uiState.update { it.copy(isLoading = false) }

            result.onSuccess {
                _uiState.update { it.copy(isEmailSent = true) }
            }.onFailure { e ->
                _uiState.update { it.copy(errorMessage = e.toUserFriendlyMessage()) }
            }
        }
    }
}
