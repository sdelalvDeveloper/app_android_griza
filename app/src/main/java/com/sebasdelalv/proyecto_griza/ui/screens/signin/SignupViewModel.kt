package com.sebasdelalv.proyecto_griza.ui.screens.signin

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignupViewModel: ViewModel() {
    // Variables para almacenar los valores de los campos
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _phone = MutableStateFlow("")
    val phone: StateFlow<String> = _phone

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordRepeat = MutableStateFlow("")
    val passwordRepeat: StateFlow<String> = _passwordRepeat

    // Variable para manejar la habilitación del botón
    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled

    // Función que actualiza el nombre de usuario
    fun onUsernameChanged(newUsername: String) {
        _username.value = newUsername
        updateButtonState()
    }

    // Función que actualiza el email
    fun onEmailChanged(newEmail: String) {
        _email.value = newEmail
        updateButtonState()
    }

    // Función que actualiza el teléfono
    fun onPhoneChanged(newPhone: String) {
        _phone.value = newPhone
        updateButtonState()
    }

    // Función que actualiza la contraseña
    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
        updateButtonState()
    }

    // Función que actualiza la repetición de la contraseña
    fun onPasswordRepeatChanged(newPasswordRepeat: String) {
        _passwordRepeat.value = newPasswordRepeat
        updateButtonState()
    }

    // Función que actualiza el estado del botón
    private fun updateButtonState() {
        // El botón se habilita si todos los campos tienen texto y las contraseñas coinciden
        _isButtonEnabled.value = _username.value.isNotEmpty() &&
                _email.value.isNotEmpty() &&
                _phone.value.isNotEmpty() &&
                _password.value.isNotEmpty() &&
                _passwordRepeat.value.isNotEmpty() &&
                _password.value == _passwordRepeat.value
    }

    fun clearFields() {
        _username.value = ""
        _email.value = ""
        _phone.value = ""
        _password.value = ""
        _passwordRepeat.value = ""
    }
}
