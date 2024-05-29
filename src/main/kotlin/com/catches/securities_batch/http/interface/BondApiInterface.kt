package com.catches.securities_batch.http.`interface`

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.dto.BondInformationResponse
import com.catches.securities_batch.http.property.ApiProperty
import org.springframework.stereotype.Service
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

@Service
interface BondApiInterface {

    val apiProperty: ApiProperty
    @FormUrlEncoded
    @GET("1160100/service/GetBondTradInfoService/getIssuIssuItemStat")
    fun getBondInformation(
        @Field("SeviceKey") serviceKey: String = apiProperty.data.key,
        @Field("pageNo") pageNo: Int,
        @Field("numOfRows") numOfRows: Int,
        @Field("resultType") resultType: String = "json",
    ): Call<BondInformationResponse>
}