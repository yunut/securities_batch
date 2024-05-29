package com.catches.securities_batch.http.dto

import kotlinx.serialization.Serializable

@Serializable
data class BondInformationResponse(
    val response: Response
)

@Serializable
data class Response(
    val body: Body,
    val header: Header
)

@Serializable
data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

@Serializable
data class Items(
    val item: List<BondItem>
)

@Serializable
data class BondItem(
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
data class Header(
    val resultCode: String,
    val resultMsg: String
)

//data class BondInformationResponse(
//    val numOfRows: Int,
//    val pageNo: Int,
//    val totalCount: Int,
//    val items: List<BondInformationDto>,
//)
//
//data class BondInformationDto(
//    val basDt: String, // 기준일자 (필수)
//    val crno: String, // 법인등록번호 (필수)
//    val bondIsurNm: String, // 채권발행인명 (필수)
//    val bondIssuDt: String, // 채권발행일자 (필수)
//    val scrsItmsKcd: String?, // 유가증권종목종류코드 (옵션)
//    val scrsItmsKcdNm: String?, // 유가증권종목종류코드명 (옵션)
//    val isinCd: String?, // ISIN코드 (옵션)
//    val isinCdNm: String?, // ISIN코드명 (옵션)
//    val bondIssuFrmtNm: String?, // 채권발행형태명 (옵션)
//    val bondExprDt: String?, // 채권만기일자 (옵션)
//    val bondIssuCurCd: String?, // 채권발행통화코드 (옵션)
//    val bondIssuCurCdNm: String?, // 채권발행통화코드명 (옵션)
//    val bondPymtAmt: Double?, // 채권납입금액 (옵션, 22,3)
//    val bondIssuAmt: Double?, // 채권발행금액 (옵션, 18,3)
//    val bondSrfcInrt: Double?, // 채권표면이율 (옵션, 15,10)
//    val irtChngDcd: String?, // 금리변동구분코드 (옵션)
//    val irtChngDcdNm: String?, // 금리변동구분코드명 (옵션)
//    val bondIntTcd: String?, // 채권이자유형코드 (옵션)
//    val bondIntTcdNm: String?, // 채권이자유형코드명 (옵션)
//)
