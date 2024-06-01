package com.catches.securities_batch

import com.catches.securities_batch.properties.HttpProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(
    HttpProperty::class,
)
class SecuritiesBatchApplication

fun main(args: Array<String>) {
    runApplication<SecuritiesBatchApplication>(*args)
}
