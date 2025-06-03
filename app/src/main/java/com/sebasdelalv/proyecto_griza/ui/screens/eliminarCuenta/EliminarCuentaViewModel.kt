package com.sebasdelalv.proyecto_griza.ui.screens.eliminarCuenta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EliminarCuentaViewModel @Inject constructor(private val repository: AuthRepository, private val reservaRepository: ReservaRepository): ViewModel() {

    // Variables para almacenar los valores de los campos
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _dialogTitle = MutableStateFlow<String?>(null)
    val dialogTitle: StateFlow<String?> = _dialogTitle

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen

    // Variable para manejar la habilitación del botón
    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled: StateFlow<Boolean> = _isButtonEnabled

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    private val _shouldNavigate = MutableStateFlow(false)
    val shouldNavigate: StateFlow<Boolean> = _shouldNavigate

    private val _isConfirmDialogOpen = MutableStateFlow(false)
    val isConfirmDialogOpen: StateFlow<Boolean> = _isConfirmDialogOpen

    fun openConfirmDialog() {
        _isConfirmDialogOpen.value = true
    }

    fun closeConfirmDialog() {
        _isConfirmDialogOpen.value = false
    }


    fun showToast(message: String) {
        _toastMessage.value = message
    }

    fun toastShown() {
        _toastMessage.value = null
        _shouldNavigate.value = true // ← navegar después del toast
    }

    fun onNavigated() {
        _shouldNavigate.value = false
    }

    // Función que actualiza el nombre de usuario
    fun onUsernameChanged(newUsername: String) {
        _username.value = newUsername
        updateButtonState()
    }

    // Función que actualiza la contraseña
    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
        updateButtonState()
    }


    // Función que actualiza el estado del botón
    private fun updateButtonState() {
        // El botón se habilita si todos los campos tienen texto y las contraseñas coinciden
        _isButtonEnabled.value = _username.value.isNotEmpty() &&
                _password.value.isNotEmpty()
    }

    fun clearFields() {
        _username.value = ""
        _password.value = ""
    }

    fun closeDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }

    fun delete(token: String){
        viewModelScope.launch {

            val result = repository.delete(token, _username.value, _password.value)
            result.fold(
                onSuccess = {
                    reservaRepository.deleteAll(token, _username.value).fold(
                        onSuccess = {},
                        onFailure = { error ->
                            _dialogTitle.value = "Error"
                            _dialogMessage.value = error.message ?: "Error desconocido"
                            _isDialogOpen.value = true
                        }
                    )
                    clearFields()
                    showToast("Borrado exitoso") // ← esto lanza el toast
                },
                onFailure = { error ->
                    _dialogTitle.value = "Error"
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }
}