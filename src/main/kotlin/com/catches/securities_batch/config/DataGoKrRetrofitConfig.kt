package com.catches.securities_batch.config

import com.catches.securities_batch.common.JsonConfig
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import com.catches.securities_batch.properties.HttpProperty
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.ConnectionPool
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import java.time.Duration
import java.util.concurrent.TimeUnit

@Configuration
class DataGoKrRetrofitConfig(
    private val httpProperty: HttpProperty,
) {
    @Bean
    @Qualifier("DataGoKrApiInterface")
    fun dataGoKrRetrofitClient(): DataGoKrApiInterface {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)

        val connectionPool = ConnectionPool(10, 5, TimeUnit.MINUTES)
        val client =
            OkHttpClient.Builder()
                .connectionPool(connectionPool)
                .callTimeout(Duration.ofMinutes(10))
                .writeTimeout(Duration.ofMinutes(10))
                .connectTimeout(Duration.ofMinutes(10))
                .readTimeout(Duration.ofSeconds(30))
                .addInterceptor(interceptor)
                .addInterceptor(
                    (
                        { chain ->
                            val request: Request =
                                chain.request().newBuilder()
                                    .addHeader("Content-Type", "application/json")
                                    .build()
                            chain.proceed(request)
                        }
                    ),
                )
                .build()

        return Retrofit.Builder().baseUrl(
            HttpUrl.Builder()
                .scheme(httpProperty.dataGoKr.scheme).host(httpProperty.dataGoKr.host)
                .build())
            .client(client)
            .addConverterFactory(JsonConfig.jsonRetrofit.asConverterFactory("application/json".toMediaTypeOrNull()!!))
            .build()
            .create(DataGoKrApiInterface::class.java)
    }
}
