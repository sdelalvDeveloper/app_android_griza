package com.sebasdelalv.proyecto_griza.ui.screens.usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.repository.AuthRepositoryImpl
import com.sebasdelalv.proyecto_griza.data.repository.ReservaRepositoryImpl
import com.sebasdelalv.proyecto_griza.domain.model.RegisterResult
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UsuariosViewModel: ViewModel() {
    private val usuarioRepository: AuthRepository = AuthRepositoryImpl()
    private val reservaRepository: ReservaRepository = ReservaRepositoryImpl()

    private val _usuarios = MutableStateFlow<List<RegisterResult>>(emptyList())
    val usuarios: StateFlow<List<RegisterResult>> = _usuarios.asStateFlow()

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    private fun showToast(message: String) {
        _toastMessage.value = message
    }

    fun closeToast() {
        _toastMessage.value = null
    }



    fun getUsuarios(token: String) {
        viewModelScope.launch {
            val result = usuarioRepository.getAll(token)
            result.fold(
                onSuccess = { listaUsuarios ->
                    if (listaUsuarios.isEmpty()) {
                        _dialogMessage.value = "No hay usuarios."
                        _isDialogOpen.value = true
                    } else {
                        _usuarios.value = listaUsuarios
                    }
                },
                onFailure = { error ->
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun eliminarUsuario(token: String, username: String) {
        viewModelScope.launch {
            val result = usuarioRepository.delete(token, username, "")
            result.fold(
                onSuccess = {
                    _usuarios.value = _usuarios.value.filterNot { it.username == username}
                    reservaRepository.deleteAll(token, username)
                    showToast("Usuario eliminado correctamente")
                    getUsuarios(token)
                },
                onFailure = { error ->
                    _dialogMessage.value = error.message ?: "Error al eliminar"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun activarBono(token: String, username: String) {
        viewModelScope.launch {
            val result = usuarioRepository.activarBono(token, username)
            result.fold(
                onSuccess = {
                    showToast("Bono activado correctamente")
                    getUsuarios(token)
                },
                onFailure = { error ->
                    _dialogMessage.value = error.message ?: "Error al eliminar"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun closeErrorDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }

}
