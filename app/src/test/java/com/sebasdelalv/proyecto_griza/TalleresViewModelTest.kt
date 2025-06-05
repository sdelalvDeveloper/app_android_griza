package com.sebasdelalv.proyecto_griza

import com.sebasdelalv.proyecto_griza.domain.model.ReservaResult
import com.sebasdelalv.proyecto_griza.domain.model.TallerResult
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import com.sebasdelalv.proyecto_griza.domain.repository.TallerRepository
import com.sebasdelalv.proyecto_griza.ui.screens.talleres.TalleresViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.mockito.Mock
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.util.Date

@OptIn(ExperimentalCoroutinesApi::class)
class TalleresViewModelTest {

    private lateinit var viewModel: TalleresViewModel

    @Mock
    private lateinit var tallerRepository: TallerRepository

    @Mock
    private lateinit var reservaRepository: ReservaRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(StandardTestDispatcher())
        viewModel = TalleresViewModel(tallerRepository, reservaRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllTalleres emits talleres on success`() = runTest {
        val fakeTalleres = listOf(
            TallerResult(
            id = "taller1",
            titulo = "Taller de Kotlin",
            descripcion = "Aprende los fundamentos de Kotlin en 3 horas.",
            fecha = Date(),
            plazas = 5,
            estado = "DISPONIBLE"
        ),
            TallerResult(
                id = "taller2",
                titulo = "Taller de Android",
                descripcion = "Crea tu primera app en Android Studio.",
                fecha = Date(),
                plazas = 0,
                estado = "LLENO"
            )
        )
        whenever(tallerRepository.getAll("token123")).thenReturn(Result.success(fakeTalleres))

        viewModel.getAllTalleres("token123")
        advanceUntilIdle()

        assertEquals(fakeTalleres, viewModel.talleres.value)
    }

    @Test
    fun `getAllTalleres emits error dialog on failure`() = runTest {
        whenever(tallerRepository.getAll("token123")).thenReturn(Result.failure(Exception("Error de red")))

        viewModel.getAllTalleres("token123")
        advanceUntilIdle()

        assertEquals("Error de red", viewModel.dialogMessage.value)
        assertTrue(viewModel.isDialogOpen.value)
    }

    @Test
    fun `insertReserva calls getAllTalleres on success`() = runTest {
        whenever(reservaRepository.insertReserva("token", "user", "tallerId"))
            .thenReturn(Result.success(mock(ReservaResult::class.java)))

        whenever(tallerRepository.getAll("token"))
            .thenReturn(Result.success(emptyList()))

        viewModel.insertReserva("token", "user", "tallerId")
        advanceUntilIdle()

        assertEquals(emptyList<TallerResult>(), viewModel.talleres.value)
    }

    @Test
    fun `insertReserva sets error dialog on failure`() = runTest {
        whenever(reservaRepository.insertReserva("token", "user", "tallerId"))
            .thenReturn(Result.failure(Exception("Reserva fallida")))

        viewModel.insertReserva("token", "user", "tallerId")
        advanceUntilIdle()

        assertEquals("Reserva fallida", viewModel.dialogMessage.value)
        assertTrue(viewModel.isDialogOpen.value)
    }

    @Test
    fun `eliminarTaller deletes reserva and updates talleres`() = runTest {
        whenever(tallerRepository.deleteTaller("token", "tallerId"))
            .thenReturn(Result.success(Unit))
        whenever(reservaRepository.deleteReservaByIdTaller("token", "tallerId"))
            .thenReturn(Result.success(Unit))
        whenever(tallerRepository.getAll("token"))
            .thenReturn(Result.success(emptyList()))

        viewModel.eliminarTaller("token", "tallerId")
        advanceUntilIdle()

        assertEquals(emptyList<TallerResult>(), viewModel.talleres.value)
    }

    @Test
    fun `eliminarTaller sets error dialog on failure`() = runTest {
        whenever(tallerRepository.deleteTaller("token", "tallerId"))
            .thenReturn(Result.failure(Exception("No se pudo eliminar")))

        viewModel.eliminarTaller("token", "tallerId")
        advanceUntilIdle()

        assertEquals("No se pudo eliminar", viewModel.dialogMessage.value)
        assertTrue(viewModel.isDialogOpen.value)
    }

    @Test
    fun `closeErrorDialog resets dialog state`() {
        viewModel.closeErrorDialog()
        assertNull(viewModel.dialogMessage.value)
        assertFalse(viewModel.isDialogOpen.value)
    }
}

