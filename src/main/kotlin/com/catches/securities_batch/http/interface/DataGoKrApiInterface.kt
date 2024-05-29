package com.catches.securities_batch.http.`interface`

import com.catches.securities_batch.http.dto.BondInformationResponse
import kotlinx.serialization.json.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataGoKrApiInterface {
    @GET("/1160100/service/GetBondTradInfoService/getIssuIssuItemStat")
    fun getBondInformation(
        @Query("ServiceKey") serviceKey: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("resultType") resultType: String,
    ): Call<BondInformationResponse>
}
