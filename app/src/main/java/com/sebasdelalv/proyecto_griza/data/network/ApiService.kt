package com.sebasdelalv.proyecto_griza.data.network

import com.sebasdelalv.proyecto_griza.data.network.dto.LoginRequest
import com.sebasdelalv.proyecto_griza.data.network.dto.LoginResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.RegisterReservaRequest
import com.sebasdelalv.proyecto_griza.data.network.dto.RegisterUserRequest
import com.sebasdelalv.proyecto_griza.data.network.dto.RegisterUserResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.ReservaResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.TallerRequest
import com.sebasdelalv.proyecto_griza.data.network.dto.TallerResponse
import com.sebasdelalv.proyecto_griza.data.network.dto.UpdatePasswordRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("usuarios/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST("usuarios/register")
    suspend fun register(
        @Body request: RegisterUserRequest
    ): Response<RegisterUserResponse>

    @GET("usuarios/{username}")
    suspend fun getUser(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<RegisterUserResponse>

    @GET("talleres/getAll")
    suspend fun getAllTalleres(
        @Header("Authorization") token: String
    ): Response<List<TallerResponse>>

    @GET("talleres/{id}")
    suspend fun getTallerById(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<TallerResponse>

    @POST("reservas/register")
    suspend fun insertReserva(
        @Header("Authorization") token: String,
        @Body request: RegisterReservaRequest
    ): Response<ReservaResponse>

    @GET("reservas/{username}")
    suspend fun getReservasByUsername(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<List<ReservaResponse>>

    @DELETE("reservas/{id}/taller/{tallerID}")
    suspend fun deleteReserva(
        @Header("Authorization") token: String,
        @Path("id") reservaId: String,
        @Path("tallerID") tallerId: String
    ): Response<Void>

    @GET("reservas/first/{username}")
    suspend fun getFirstReservaByUsername(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<ReservaResponse?>

    @DELETE("usuarios/delete/{username}/{password}")
    suspend fun deleteUser(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Path("password") password: String,
    ): Response<RegisterUserResponse?>

    @POST("usuarios/updatePassword")
    suspend fun updatePassword(
        @Header("Authorization") token: String,
        @Body usuario: UpdatePasswordRequest
    ): Response<Boolean>

    @DELETE("reservas/deleteAll/{username}")
    suspend fun deleteReservaAllByUsername(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): Response<Void>

    @GET("usuarios/getAll")
    suspend fun getAllUser(
        @Header("Authorization") token: String
    ): Response<List<RegisterUserResponse>>

    @POST("usuarios/activarBono/{username}")
    suspend fun activarBono(
        @Header("Authorization") token: String,
        @Path("username") username: String,
    ): Response<RegisterUserResponse>

    @POST("talleres/update/{id}")
    suspend fun modificarTaller(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body taller: TallerRequest
    ): Response<TallerResponse>

    @POST("talleres/register")
    suspend fun insertTaller(
        @Header("Authorization") token: String,
        @Body taller: TallerRequest
    ): Response<TallerResponse>

    @DELETE("talleres/delete/{tallerID}")
    suspend fun deleteTaller(
        @Header("Authorization") token: String,
        @Path("tallerID") tallerID: String
    ): Response<Void>

    @DELETE("reservas/delete/{id}")
    suspend fun deleteReservasByIdTaller(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Void>

}