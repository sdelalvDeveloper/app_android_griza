package com.sebasdelalv.proyecto_griza

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.sebasdelalv.proyecto_griza.data.local.ISessionManager
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.fakes.FakeSessionManager
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.Date
import kotlin.test.Test

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class TalleresScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: TalleresViewModel

    @Before
    fun setup() {
        // Mockeamos ViewModel
        viewModel = mock(TalleresViewModel::class.java)

        // Preparar flujos simulados
        val talleresFlow = MutableStateFlow(
            listOf(
                TallerResult(
                    id = "t1",
                    titulo = "Taller 1",
                    descripcion = "Descripcion",
                    fecha = Date(),
                    plazas = 5,
                    estado = "DISPONIBLE"
                )
            )
        )
        `when`(viewModel.talleres).thenReturn(talleresFlow)
        `when`(viewModel.dialogMessage).thenReturn(MutableStateFlow(null))
        `when`(viewModel.isDialogOpen).thenReturn(MutableStateFlow(false))
    }

    private fun setContentWithSession(role: String): FakeSessionManager {
        val sessionManager = FakeSessionManager(role = role)

        composeTestRule.setContent {
            TalleresScreenTestable(
                viewModel = viewModel,
                sessionManager = sessionManager,
                navigateToBack = {},
                navigateToMenu = {},
                navigateToTalleres = {},
                navigateToInfo = {},
                navigateToModificarTaller = { _, _ -> },
                navigateToMenuAdmin = {}
            )
        }
        return sessionManager
    }

    @Test
    fun fabVisible_onlyForAdmin() {
        // Admin: botón debe mostrarse
        setContentWithSession(role = "admin")
        composeTestRule.onNodeWithContentDescription("Agregar Taller").assertIsDisplayed()
    }

    @Test
    fun fabNotVisible_forUser() {
        // Usuario: botón no debe mostrarse
        setContentWithSession(role = "user")
        composeTestRule.onNodeWithContentDescription("Agregar Taller").assertDoesNotExist()
    }

    @Test
    fun clickingTaller_showsReservarDialog_forUser() {
        setContentWithSession(role = "user")
        composeTestRule.onNodeWithText("Taller 1").performClick()
        composeTestRule.onNodeWithText("Aceptar").assertIsDisplayed()
    }

    @Test
    fun clickingTaller_showsAdminDialog_forAdmin() {
        setContentWithSession(role = "admin")
        composeTestRule.onNodeWithText("Taller 1").performClick()
        composeTestRule.onNodeWithText("Modificar").assertIsDisplayed()
        composeTestRule.onNodeWithText("Eliminar").assertIsDisplayed()
    }

    @Test
    fun reservarButton_callsInsertReserva() {
        val sessionManager = setContentWithSession(role = "user")

        // Click para abrir dialog
        composeTestRule.onNodeWithText("Taller 1").performClick()

        // Click en botón "Aceptar" para reservar
        composeTestRule.onNodeWithText("Aceptar").performClick()

        // Verificar llamada al ViewModel
        verify(viewModel).insertReserva(
            eq(sessionManager.getToken().toString()),
            eq(sessionManager.getUsername().toString()),
            eq("t1")
        )
    }


    @Test
    fun eliminarTaller_calledOnAdmin() {
        val sessionManager = setContentWithSession(role = "admin")

        // Abrir diálogo admin (al hacer click en el taller)
        composeTestRule.onNodeWithText("Taller 1").performClick()

        // Click en botón eliminar
        composeTestRule.onNodeWithText("Eliminar").performClick()

        // Verificar que se llamó eliminarTaller con token y id correcto
        verify(viewModel).eliminarTaller(
            eq(sessionManager.getToken().toString()),
            eq("t1")
        )
    }
}

