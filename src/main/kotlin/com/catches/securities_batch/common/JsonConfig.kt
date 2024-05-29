package com.catches.securities_batch.common

import kotlinx.serialization.json.Json

object JsonConfig {
    @JvmStatic
    val jsonRetrofit = Json {
        isLenient = true
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
}