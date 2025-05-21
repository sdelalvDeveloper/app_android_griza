package com.sebasdelalv.proyecto_griza.ui.screens.menu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.repository.ReservaRepositoryImpl
import com.sebasdelalv.proyecto_griza.data.repository.TallerRepositoryImpl
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import com.sebasdelalv.proyecto_griza.domain.repository.TallerRespository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MenuViewModel: ViewModel() {
    private val tallerRepository: TallerRespository = TallerRepositoryImpl()
    private val reservaRepository: ReservaRepository = ReservaRepositoryImpl()

    private val _talleres = MutableStateFlow<List<TallerResult>>(emptyList())
    val talleres: StateFlow<List<TallerResult>> = _talleres.asStateFlow()

    private val _reserva = MutableStateFlow<ReservaResult?>(null)
    val reserva: StateFlow<ReservaResult?> = _reserva.asStateFlow()

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen

    fun getAllTalleres(token: String) {
        viewModelScope.launch {
            val result = tallerRepository.getAll(token)
            result.fold(
                onSuccess = { it ->
                    _talleres.value = it
                },
                onFailure = { error ->
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun insertReserva(token: String, username: String, tallerId: String) {
        viewModelScope.launch {
            val result = reservaRepository.insertReserva(token, username, tallerId)
            result.fold(
                onSuccess = { it ->
                    getAllTalleres(token)
                    getFirstReserva(token, username)
                },
                onFailure = { error ->
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun getFirstReserva(token: String, username: String) {
        viewModelScope.launch {
            val result = reservaRepository.getFirst(token, username)
            result.fold(
                onSuccess = { it ->
                    _reserva.value = it
                },
                onFailure = { error ->
                    _reserva.value = null
                    _dialogMessage.value = error.message ?: "Error desconocido"
                }
            )
        }
    }

    fun closeErrorDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }
}