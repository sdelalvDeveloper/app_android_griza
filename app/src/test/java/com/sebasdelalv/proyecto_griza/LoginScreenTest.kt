package com.sebasdelalv.proyecto_griza


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.sebasdelalv.proyecto_griza.fakes.FakeAuthRepository
import com.sebasdelalv.proyecto_griza.ui.screens.login.LoginViewModel
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeAuthRepository: FakeAuthRepository
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        fakeAuthRepository = FakeAuthRepository()
        viewModel = LoginViewModel(fakeAuthRepository)
    }

    @Test
    fun testLoginSuccess_navigateToMenuCalled() {
        var navigateToMenuCalled = false

        composeTestRule.setContent {
            LoginScreenTestable(
                viewModel = viewModel,
                navigateToSignup = {},
                navigateToMenu = { navigateToMenuCalled = true },
                navigateToMenuAdmin = {}
            )
        }

        composeTestRule.onNodeWithText("Nombre de usuario").performTextInput("test@example.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("password")

        // Asegurarse que el botón está habilitado antes de hacer click
        composeTestRule.onNodeWithTag("buttonSession").assertIsEnabled()

        composeTestRule.onNodeWithTag("buttonSession").performClick()
        composeTestRule.waitForIdle()

        assertTrue("navigateToMenu no fue llamado", navigateToMenuCalled)
    }

    @Test
    fun testLoginFailure_showsErrorDialog() {

        composeTestRule.setContent {
            LoginScreenTestable(
                viewModel = viewModel,
                navigateToSignup = {},
                navigateToMenu = {},
                navigateToMenuAdmin = {}
            )
        }

        composeTestRule.onNodeWithText("Nombre de usuario").performTextInput("wrong@example.com")
        composeTestRule.onNodeWithText("Contraseña").performTextInput("wrongpass")

        composeTestRule.onNodeWithTag("buttonSession").assertIsEnabled()
        composeTestRule.onNodeWithTag("buttonSession").performClick()

        composeTestRule.waitForIdle()

        composeTestRule.onNodeWithText("Error").assertIsDisplayed()
        composeTestRule.onNodeWithText("Credenciales inválidas").assertIsDisplayed()
    }
}


