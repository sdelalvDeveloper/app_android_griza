package com.sebasdelalv.proyecto_griza.ui.screens.reservas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservasViewModel @Inject constructor(private val reservaRepository: ReservaRepository) : ViewModel() {

    private val _reservas = MutableStateFlow<List<ReservaResult>>(emptyList())
    val reservas: StateFlow<List<ReservaResult>> = _reservas.asStateFlow()

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> = _toastMessage

    fun showToast(message: String) {
        _toastMessage.value = message
    }

    fun toastShown() {
        _toastMessage.value = null
    }

    fun getReservasUsuario(token: String, username: String) {
        viewModelScope.launch {
            val result = reservaRepository.getReservaByUsername(token, username)
            result.fold(
                onSuccess = { listaReservas ->
                    if (listaReservas.isEmpty()) {
                        _dialogMessage.value = "No hay reservas."
                        _isDialogOpen.value = true
                    } else {
                        _reservas.value = listaReservas
                    }
                },
                onFailure = { error ->
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun eliminarReserva(token: String, reserva: ReservaResult) {
        viewModelScope.launch {
            val result = reservaRepository.deleteReservaById(token, reserva.id, reserva.tallerID)
            result.fold(
                onSuccess = {
                    _reservas.value = _reservas.value.filterNot { it.id == reserva.id }
                    showToast("Reserva eliminada")
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