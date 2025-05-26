package com.sebasdelalv.proyecto_griza.ui.screens.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.repository.AuthRepositoryImpl
import com.sebasdelalv.proyecto_griza.data.local.SessionManager
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val repository: AuthRepository = AuthRepositoryImpl()

    // Estado del nombre de usuario
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    // Estado de la contraseña
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password


    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen

    // Estado del botón: true si ambos campos están rellenos
    val isButtonEnabled: StateFlow<Boolean> = combine(_username, _password) { user, pass ->
        user.isNotBlank() && pass.isNotBlank()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    // Métodos para actualizar el estado
    fun onUsernameChanged(value: String) {
        _username.value = value
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
    }

    // Aquí podrías añadir una función login()
    fun onLoginClick(sessionManager: SessionManager, onSuccess: (String) -> Unit) {
        viewModelScope.launch {

            val result = repository.login(_username.value, _password.value)
            result.fold(
                onSuccess = { loginResult ->
                    sessionManager.saveUserSession(_username.value, loginResult.token, loginResult.role, _password.value)
                    clearFields()
                    onSuccess(loginResult.role)
                },
                onFailure = { error ->
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun showError(message: String) {
        _dialogMessage.value = message
        _isDialogOpen.value = true
    }

    fun closeErrorDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }

    // Limpia los campos
    fun clearFields() {
        _username.value = ""
        _password.value = ""
    }
}
