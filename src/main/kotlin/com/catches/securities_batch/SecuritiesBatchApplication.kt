package com.catches.securities_batch

import com.catches.securities_batch.http.property.ApiProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ApiProperty::class)
class SecuritiesBatchApplication

fun main(args: Array<String>) {
    runApplication<SecuritiesBatchApplication>(*args)
}
