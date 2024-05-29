package com.catches.securities_batch.controller

import com.catches.securities_batch.http.`interface`.BondApiInterface
import com.catches.securities_batch.service.BondService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import retrofit2.Retrofit

@RestController
class BondController(
    private val bondService: BondService
) {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/test")
    fun test(): String {
        bondService.getBondInformation()

        return "test success"
    }
}