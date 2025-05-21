package com.sebasdelalv.proyecto_griza.ui.screens.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.repository.AuthRepositoryImpl
import com.sebasdelalv.proyecto_griza.data.repository.ReservaRepositoryImpl
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.fold

class PerfilViewModel() : ViewModel() {

    private val repository: AuthRepository = AuthRepositoryImpl()
    private val reservaRepository: ReservaRepository = ReservaRepositoryImpl()

    private val _saldo = MutableStateFlow<Int?>(null)
    val saldo: StateFlow<Int?> = _saldo

    private val _reserva = MutableStateFlow<ReservaResult?>(null)
    val reserva: StateFlow<ReservaResult?> = _reserva.asStateFlow()

    private val _dialogTitle = MutableStateFlow<String?>(null)
    val dialogTitle: StateFlow<String?> = _dialogTitle

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen


    fun loadSaldo(username: String, token: String) {
        viewModelScope.launch {
            val result = repository.get(username, token)
            result.fold(
                onSuccess = { getResult ->
                    _saldo.value = getResult.bono
                },
                onFailure = { error ->
                    _dialogTitle.value = "Error"
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

    fun closeDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }
}