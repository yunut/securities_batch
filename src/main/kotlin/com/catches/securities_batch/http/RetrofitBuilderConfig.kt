package com.catches.securities_batch.http

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

@Configuration
class RetrofitBuilderConfig() {

    @Bean
    @Primary
    fun getOkHttpClient(): Retrofit.Builder {
        val logLevel = HttpLoggingInterceptor.Level.BODY

        val interceptor = HttpLoggingInterceptor().setLevel(logLevel)

        val connectionPool = ConnectionPool(30, 5, TimeUnit.MINUTES)

        val client = OkHttpClient.Builder()
            .connectionPool(connectionPool)
            .addInterceptor(interceptor)
            .build()

        // return Retrofit Builder
        return Retrofit.Builder()
            .client(client)

    }
}