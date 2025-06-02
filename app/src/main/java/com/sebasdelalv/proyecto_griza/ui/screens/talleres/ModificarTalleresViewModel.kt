package com.sebasdelalv.proyecto_griza.ui.screens.talleres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.network.dto.TallerRequest
import com.sebasdelalv.proyecto_griza.data.repository.TallerRepositoryImpl
import com.sebasdelalv.proyecto_griza.domain.repository.TallerRespository
import com.sebasdelalv.proyecto_griza.utils.esFechaValida
import com.sebasdelalv.proyecto_griza.utils.esHoraValida
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ModificarTalleresViewModel: ViewModel() {
    private val repository: TallerRespository = TallerRepositoryImpl()
    // Variables para almacenar los valores de los campos
    private val _titulo = MutableStateFlow("")
    val titulo: StateFlow<String> = _titulo

    private val _descripcion = MutableStateFlow("")
    val descripcion: StateFlow<String> = _descripcion

    private val _fecha = MutableStateFlow("")
    val fecha: StateFlow<String> = _fecha

    private val _hora = MutableStateFlow("")
    val hora: StateFlow<String> = _hora

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
    fun onTituloChanged(newTitulo: String) {
        _titulo.value = newTitulo
        updateButtonState()
    }

    // Función que actualiza la contraseña
    fun onDescripcionChanged(newDescripcion: String) {
        _descripcion.value = newDescripcion
        updateButtonState()
    }

    fun onFechaChanged(newPassword: String) {
        _fecha.value = newPassword
        updateButtonState()
    }

    fun onHoraChanged(newHora: String) {
        _hora.value = newHora
        updateButtonState()
    }


    // Función que actualiza el estado del botón
    private fun updateButtonState() {
        // El botón se habilita si todos los campos tienen texto y las contraseñas coinciden
        _isButtonEnabled.value = _titulo.value.isNotEmpty()
                && _descripcion.value.isNotEmpty()
                && _fecha.value.isNotEmpty()
                && _hora.value.isNotEmpty()
    }

    fun clearFields() {
        _titulo.value = ""
        _descripcion.value = ""
        _fecha.value = ""
        _hora.value = ""
    }

    fun closeDialog() {
        _isDialogOpen.value = false
        _dialogMessage.value = null
    }

    fun modificarTaller(token: String, id: String){
        if (!esFechaValida(_fecha.value)) {
            _dialogTitle.value = "Fecha inválida"
            _dialogMessage.value = "La fecha debe tener el formato dd/MM/yyyy"
            _isDialogOpen.value = true
            return
        }

        if (!esHoraValida(_hora.value)) {
            _dialogTitle.value = "Hora inválida"
            _dialogMessage.value = "La hora debe tener el formato HH:mm"
            _isDialogOpen.value = true
            return
        }
        val taller = TallerRequest(
            titulo = _titulo.value,
            descripcion = _descripcion.value,
            fecha = combinarFechaYHora(_fecha.value, _hora.value).toString()
        )

        viewModelScope.launch {

            val result = repository.updateTaller(token, id, taller)
            result.fold(
                onSuccess = {
                    clearFields()
                    showToast("Taller actualizado") // ← esto lanza el toast
                },
                onFailure = { error ->
                    _dialogTitle.value = "Error"
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    fun insertarTaller(token: String) {
        if (!esFechaValida(_fecha.value)) {
            _dialogTitle.value = "Fecha inválida"
            _dialogMessage.value = "La fecha debe tener el formato dd/MM/yyyy"
            _isDialogOpen.value = true
            return
        }

        if (!esHoraValida(_hora.value)) {
            _dialogTitle.value = "Hora inválida"
            _dialogMessage.value = "La hora debe tener el formato HH:mm"
            _isDialogOpen.value = true
            return
        }
        val taller = TallerRequest(
            titulo = _titulo.value,
            descripcion = _descripcion.value,
            fecha = combinarFechaYHora(_fecha.value, _hora.value).toString()
        )

        viewModelScope.launch {
            val result = repository.insertTaller(token, taller)
            result.fold(
                onSuccess = {
                    clearFields()
                    showToast("Taller creado") // ← esto lanza el toast
                },
                onFailure = { error ->
                    _dialogTitle.value = "Error"
                    _dialogMessage.value = error.message ?: "Error desconocido"
                    _isDialogOpen.value = true
                }
            )
        }
    }

    private fun combinarFechaYHora(fecha: String, hora: String): String? {
        return try {
            val inputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            inputFormat.isLenient = false
            inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Asume entrada como UTC

            val date = inputFormat.parse("$fecha $hora") ?: return null

            val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US)
            outputFormat.timeZone = TimeZone.getTimeZone("UTC") // Salida en UTC ISO 8601

            outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }



}