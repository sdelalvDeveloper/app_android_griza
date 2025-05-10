package com.sebasdelalv.proyecto_griza.ui.screens.perfil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PerfilViewModel(
   // private val api: PerfilApi
) : ViewModel() {
/*
    private val _uiState = MutableStateFlow(PerfilUiState(loading = true))
    val uiState: StateFlow<PerfilUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = PerfilUiState(loading = true)
            runCatching {
                api.getProximaCita()
            }.onSuccess { dto ->
                _uiState.value = PerfilUiState(
                    loading = false,
                    dia = dto.dia,
                    mes = dto.mes,
                    hora = dto.hora
                )
            }.onFailure { err ->
                _uiState.value = PerfilUiState(
                    loading = false,
                    error = err.localizedMessage ?: "Error desconocido"
                )
            }
        }
    }*/
}