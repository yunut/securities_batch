package com.catches.securities_batch.http.dto

data class BondInformationResponse(
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int,
    val items: List<BondInformationDto>
)
data class BondInformationDto(
    val basDt: String, // 기준일자 (필수)
    val crno: String, // 법인등록번호 (필수)
    val bondIsurNm: String, // 채권발행인명 (필수)
    val bondIssuDt: String, // 채권발행일자 (필수)
    val scrsItmsKcd: String?, // 유가증권종목종류코드 (옵션)
    val scrsItmsKcdNm: String?, // 유가증권종목종류코드명 (옵션)
    val isinCd: String?, // ISIN코드 (옵션)
    val isinCdNm: String?, // ISIN코드명 (옵션)
    val bondIssuFrmtNm: String?, // 채권발행형태명 (옵션)
    val bondExprDt: String?, // 채권만기일자 (옵션)
    val bondIssuCurCd: String?, // 채권발행통화코드 (옵션)
    val bondIssuCurCdNm: String?, // 채권발행통화코드명 (옵션)
    val bondPymtAmt: Double?, // 채권납입금액 (옵션, 22,3)
    val bondIssuAmt: Double?, // 채권발행금액 (옵션, 18,3)
    val bondSrfcInrt: Double?, // 채권표면이율 (옵션, 15,10)
    val irtChngDcd: String?, // 금리변동구분코드 (옵션)
    val irtChngDcdNm: String?, // 금리변동구분코드명 (옵션)
    val bondIntTcd: String?, // 채권이자유형코드 (옵션)
    val bondIntTcdNm: String? // 채권이자유형코드명 (옵션)
)