package com.sebasdelalv.proyecto_griza.data.network

import com.sebasdelalv.proyecto_griza.data.repository.AuthRepositoryImpl
import com.sebasdelalv.proyecto_griza.data.repository.ReservaRepositoryImpl
import com.sebasdelalv.proyecto_griza.data.repository.TallerRepositoryImpl
import com.sebasdelalv.proyecto_griza.domain.repository.AuthRepository
import com.sebasdelalv.proyecto_griza.domain.repository.ReservaRepository
import com.sebasdelalv.proyecto_griza.domain.repository.TallerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.103:8080/")
            .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a objetos
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAuthRepository(api: ApiService): AuthRepository {
        return AuthRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideReservaRepository(api: ApiService): ReservaRepository {
        return ReservaRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideTallerRepository(api: ApiService): TallerRepository {
        return TallerRepositoryImpl(api)
    }
}