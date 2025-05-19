package com.sebasdelalv.proyecto_griza.ui.screens.infoPersonal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.repository.AuthRepositoryImpl
import com.sebasdelalv.proyecto_griza.domain.model.RegisterResult
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InfoPersonalViewModel : ViewModel() {

    private val repository: AuthRepository = AuthRepositoryImpl()

    private val _userInfo = MutableStateFlow<RegisterResult?>(null)
    val userInfo: StateFlow<RegisterResult?> = _userInfo

    private val _dialogTitle = MutableStateFlow<String?>(null)
    val dialogTitle: StateFlow<String?> = _dialogTitle

    private val _dialogMessage = MutableStateFlow<String?>(null)
    val dialogMessage: StateFlow<String?> = _dialogMessage

    private val _isDialogOpen = MutableStateFlow(false)
    val isDialogOpen: StateFlow<Boolean> = _isDialogOpen


    fun fetchUserInfo(username: String, token: String) {
        viewModelScope.launch {

            val result = repository.get(username, token)
            result.fold(
                onSuccess = { getResult ->
                    _userInfo.value = getResult

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

