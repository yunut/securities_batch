package com.catches.securities_batch.http.property

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "api")
data class ApiProperty (
    val data: dataProperties
){
    data class dataProperties(
        val key: String
    )
}