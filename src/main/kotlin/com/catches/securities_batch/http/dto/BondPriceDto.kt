package com.catches.securities_batch.http.dto

import kotlinx.serialization.Serializable

@Serializable
data class BondPriceDto(
    val basDt: String,
    val srtnCd: String,
    val isinCd: String,
    val itmsNm: String,
    val mrktCtg: String,
    val xpYrCnt: String?,
    val itmsCtg: String?,
    val clprPrc: Double,
    val clprVs: String,
    val clprBnfRt: Double,
    val mkpPrc: Double,
    val mkpBnfRt: Double,
    val hiprPrc: Double,
    val hiprBnfRt: Double,
    val loprPrc: Double,
    val loprBnfRt: Double,
    val trqu: String,
    val trPrc: String
)

@Serializable
data class BondPriceItems(
    val item: List<BondPriceDto>
)

@Serializable
data class BondPriceBody(
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int,
    val items: BondPriceItems
)

@Serializable
data class BondPriceResponse(
    val body: BondPriceBody,
    val header: Header
)

@Serializable
data class BondPriceApiResponse(
    val response: BondPriceResponse
)