package com.catches.securities_batch.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "http")
data class HttpProperty(
    val dataGoKr: UrlEntity,
)

data class UrlEntity(
    val key: String,
    val scheme: String,
    val host: String,
)
