package com.sebasdelalv.proyecto_griza.ui.screens.menuAdmin

import androidx.lifecycle.ViewModel
import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import com.sebasdelalv.proyecto_griza.domain.repository.TallerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MenuAdminViewModel @Inject constructor(
    private val tallerRepository: TallerRepository,
    private val reservaRepository: ReservaRepository
): ViewModel() {

    private val _talleres = MutableStateFlow<List<TallerResult>>(emptyList())
    val talleres: StateFlow<List<TallerResult>> = _talleres.asStateFlow()

    private val _reserva = MutableStateFlow<ReservaResult?>(null)
    val reserva: StateFlow<ReservaResult?> = _reserva.asStateFlow()

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen


    fun closeErrorDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }
}