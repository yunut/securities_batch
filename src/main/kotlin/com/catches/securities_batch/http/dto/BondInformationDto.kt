package com.catches.securities_batch.http.dto

import kotlinx.serialization.Serializable

@Serializable
data class BondInformationDto(
    val basDt: String,
    val crno: String,
    val scrsItmsKcd: String,
    val isinCd: String,
    val scrsItmsKcdNm: String,
    val bondIsurNm: String,
    val isinCdNm: String,
    val bondIssuDt: String,
    val bondIssuFrmtNm: String,
    val bondIssuAmt: String,
    val bondIssuCurCd: String,
    val bondIssuCurCdNm: String,
    val bondExprDt: String,
    val bondPymtAmt: String,
    val irtChngDcd: String,
    val irtChngDcdNm: String,
    val bondSrfcInrt: String,
    val bondIntTcd: String,
    val bondIntTcdNm: String
)

@Serializable
data class BondInformationItems(
    val item: List<BondInformationDto>
)

@Serializable
data class BondInformationBody(
    val items: BondInformationItems,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

@Serializable
data class Header(
    val resultCode: String,
    val resultMsg: String
)

@Serializable
data class BondInformationResponse(
    val body: BondInformationBody,
    val header: Header
)

@Serializable
data class BondInformationApiResponse(
    val response: BondInformationResponse
)