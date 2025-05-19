package com.sebasdelalv.proyecto_griza.ui.screens.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.repository.AuthRepositoryImpl
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.fold

class PerfilViewModel() : ViewModel() {

    private val repository: AuthRepository = AuthRepositoryImpl()

    private val _saldo = MutableStateFlow<Int?>(null)
    val saldo: StateFlow<Int?> = _saldo

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

    fun closeDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }
}