package com.catches.securities_batch.container

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class TestContainer {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/")
    fun test(): String {
        return "test success"
    }
}