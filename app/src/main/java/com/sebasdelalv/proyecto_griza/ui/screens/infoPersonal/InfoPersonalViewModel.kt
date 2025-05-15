package com.sebasdelalv.proyecto_griza.ui.screens.infoPersonal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasdelalv.proyecto_griza.data.user.UserInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InfoPersonalViewModel : ViewModel() {

    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> = _userInfo

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        fetchUserInfo()
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            _isLoading.value = true
            /* llamada API*/
            _userInfo.value = UserInfo(
                name = "Juan Pérez",
                email = "juan.perez@email.com",
                phone = "+34 600 123 456"
            )
            _isLoading.value = false
        }
    }
}

