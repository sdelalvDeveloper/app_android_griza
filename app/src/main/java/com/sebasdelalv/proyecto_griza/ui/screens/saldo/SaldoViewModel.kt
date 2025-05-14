package com.sebasdelalv.proyecto_griza.ui.screens.saldo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SaldoViewModel(/*private val saldoApi: SaldoApi*/) : ViewModel() {

    /*private val _saldo = MutableStateFlow<SaldoResponse?>(null)
    val saldo: StateFlow<SaldoResponse?> = _saldo

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadSaldo()
    }

    private fun loadSaldo() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = saldoApi.getSaldo()
                _saldo.value = result
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Error al cargar saldo"
            } finally {
                _isLoading.value = false
            }
        }
    }*/
}
