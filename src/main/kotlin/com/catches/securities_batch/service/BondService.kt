package com.catches.securities_batch.service

import com.catches.securities_batch.http.`interface`.BondApiInterface
import org.springframework.stereotype.Service
import retrofit2.Retrofit

@Service
class BondService(
    final val retrofit: Retrofit
) {
    private val testService = retrofit.create(BondApiInterface::class.java)

    fun getBondInformation() {
        testService.getBondInformation(pageNo = 1, numOfRows = 1)
    }
}