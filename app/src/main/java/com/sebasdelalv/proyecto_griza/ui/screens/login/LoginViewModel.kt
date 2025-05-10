package com.sebasdelalv.proyecto_griza.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class LoginViewModel: ViewModel() {

    // Estado del nombre de usuario
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    // Estado de la contraseña
    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

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
    fun onLoginClick() {
        // Lógica de login (llamar a un UseCase, etc.)
    }

    // Limpia los campos
    fun clearFields() {
        _username.value = ""
        _password.value = ""
    }
}
