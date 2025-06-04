package com.sebasdelalv.proyecto_griza

import com.sebasdelalv.proyecto_griza.domain.model.LoginResult
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.mockito.kotlin.any


@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: AuthRepository

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        repository = mock()
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun `username and password updates update state correctly`() = runTest {
        viewModel.onUsernameChanged("admin")
        viewModel.onPasswordChanged("1234")
        assertEquals("admin", viewModel.username.value)
        assertEquals("1234", viewModel.password.value)
        assertTrue(viewModel.isButtonEnabled.value)
    }

    @Test
    fun `login success calls onSuccess with correct role`() = runTest {
        val loginResult = LoginResult(token = "token123", role = "admin")
        whenever(repository.login("admin", "1234")).thenReturn(Result.success(loginResult))

        val onSuccessMock: (String) -> Unit = mock()
        viewModel.onUsernameChanged("admin")
        viewModel.onPasswordChanged("1234")

        viewModel.onLoginClick(mock(), onSuccessMock)

        verify(onSuccessMock).invoke("admin")
    }

    @Test
    fun `login failure sets error dialog`() = runTest {
        whenever(repository.login(any(), any()))
            .thenReturn(Result.failure(Exception("Invalid credentials")))

        viewModel.onUsernameChanged("user")
        viewModel.onPasswordChanged("wrong")

        viewModel.onLoginClick(mock(), {})

        assertEquals("Invalid credentials", viewModel.dialogMessage.value)
        assertTrue(viewModel.isDialogOpen.value)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
