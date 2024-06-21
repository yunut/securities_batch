package com.catches.securities_batch.http.`interface`

import com.catches.securities_batch.http.dto.BondInformationApiResponse
import com.catches.securities_batch.http.dto.BondPriceApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DataGoKrApiInterface {
    @GET("/1160100/service/GetBondIssuInfoService/getBondBasiInfo")
    fun getBondInformation(
        @Query("ServiceKey") serviceKey: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("resultType") resultType: String,
        @Query("basDt") basDt: String,
    ): Call<BondInformationApiResponse>

    @GET("/1160100/service/GetBondSecuritiesInfoService/getBondPriceInfo")
    fun getBondPrice(
        @Query("ServiceKey") serviceKey: String,
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("resultType") resultType: String,
        @Query("beginBasDt") beginBasDt: String,
    ): Call<BondPriceApiResponse>
}
