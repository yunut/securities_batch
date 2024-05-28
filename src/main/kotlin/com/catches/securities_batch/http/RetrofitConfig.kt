package com.catches.securities_batch.http

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json

import okhttp3.ConnectionPool
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


@Configuration
class RetrofitConfig() {

    @Bean
    @Primary
    fun getRetrofitClient(): Retrofit {
        val logLevel = HttpLoggingInterceptor.Level.BODY

        val interceptor = HttpLoggingInterceptor().setLevel(logLevel)
        val connectionPool = ConnectionPool(30, 5, TimeUnit.MINUTES)

        val client = OkHttpClient.Builder()
            .connectionPool(connectionPool)
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
    }
}