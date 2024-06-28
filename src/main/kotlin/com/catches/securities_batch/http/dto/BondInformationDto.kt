package com.catches.securities_batch.http.dto

import kotlinx.serialization.Serializable

@Serializable
data class BondInformationDto(
    val basDt: String, // 기준일자 (필수)
    val crno: String, // 법인등록번호 (필수)
    val isinCd: String? = null, // ISIN코드 (옵션)
    val isinCdNm: String? = null, // ISIN코드명 (옵션)
    val scrsItmsKcd: String? = null, // 유가증권종목종류코드 (옵션)
    val scrsItmsKcdNm: String? = null, // 유가증권종목종류코드명 (옵션)
    val bondIssuCurCd: String? = null, // 채권발행통화코드 (옵션)
    val bondIssuCurCdNm: String? = null, // 채권발행통화코드명 (옵션)
    val bondIsurNm: String, // 채권발행인명 (필수)
    val sicNm: String? = null, // 표준산업분류명 (옵션)
    val bondIssuDt: String? = null, // 채권발행일자 (옵션)
    val bondExprDt: String? = null, // 채권만기일자 (옵션)
    val irtChngDcd: String? = null, // 금리변동구분코드 (옵션)
    val irtChngDcdNm: String? = null, // 금리변동구분코드명 (옵션)
    val bondSrfcInrt: Double? = null, // 채권표면이율 (옵션)
    val grnDcd: String? = null, // 보증구분코드 (옵션)
    val grnDcdNm: String? = null, // 보증구분코드명 (옵션)
    val bondRnknDcd: String? = null, // 채권순위구분코드 (옵션)
    val bondRnknDcdNm: String? = null, // 채권순위구분코드명 (옵션)
    val optnTcd: String? = null, // 옵션유형코드 (옵션)
    val optnTcdNm: String? = null, // 옵션유형코드명 (옵션)
    val pclrBondKcd: String? = null, // 특이채권종류코드 (옵션)
    val pclrBondKcdNm: String? = null, // 특이채권종류코드명 (옵션)
    val bondIssuAmt: Double? = null, // 채권발행금액 (옵션)
    val bondPymtAmt: Double? = null, // 채권납입금액 (옵션)
    val bondBal: Double? = null, // 채권잔액 (옵션)
    val bondOffrMcd: String? = null, // 채권모집방법코드 (옵션)
    val bondOffrMcdNm: String? = null, // 채권모집방법코드명 (옵션)
    val lstgDt: String? = null, // 상장일자 (옵션)
    val txtnDcd: String? = null, // 과세구분코드 (옵션)
    val txtnDcdNm: String? = null, // 과세구분코드명 (옵션)
    val pamtRdptMcd: String? = null, // 원금상환방법코드 (옵션)
    val pamtRdptMcdNm: String? = null, // 원금상환방법코드명 (옵션)
    val stripsPsblYn: String? = null, // 스트립스채권가능여부 (옵션)
    val stripsNm: String? = null, // 스트립스채권명 (옵션)
    val prisLnkgBondYn: String? = null, // 물가연동채권여부 (옵션)
    val piamPayInstNm: String? = null, // 원리금지급기관명 (옵션)
    val piamPayBrofNm: String? = null, // 원리금지급지점명 (옵션)
    val cptUsgeDcd: String? = null, // 자금용도구분코드 (옵션)
    val cptUsgeDcdNm: String? = null, // 자금용도구분코드명 (옵션)
    val bondRegInstDcd: String? = null, // 채권등록기관구분코드 (옵션)
    val bondRegInstDcdNm: String? = null, // 채권등록기관구분코드명 (옵션)
    val issuDptyNm: String? = null, // 발행대리인명 (옵션)
    val bondUndtInstNm: String? = null, // 채권인수기관명 (옵션)
    val bondGrnInstNm: String? = null, // 채권보증기관명 (옵션)
    val cpbdMngCmpyNm: String? = null, // 사채관리회사명 (옵션)
    val crfndYn: String? = null, // 크라우드펀딩여부 (옵션)
    val prmncBondYn: String? = null, // 영구채권여부 (옵션)
    val qibTrgtScrtYn: String? = null, // QIB대상증권여부 (옵션)
    val prmncBondTmnDt: String? = null, // 영구채권해지일자 (옵션)
    val rgtExertMnbdDcd: String? = null, // 권리행사주체구분코드 (옵션)
    val rgtExertMnbdDcdNm: String? = null, // 권리행사주체구분코드명 (옵션)
    val intCmpuMcd: String? = null, // 이자산정방법코드 (옵션)
    val intCmpuMcdNm: String? = null, // 이자산정방법코드명 (옵션)
    val qibTmnDt: String? = null, // QIB해지일자 (옵션)
    val bondIntTcd: String? = null, // 채권이자유형코드 (옵션)
    val bondIntTcdNm: String? = null, // 채권이자유형코드명 (옵션)
    val intPayCyclCtt: String? = null, // 이자지급주기내용 (옵션)
    val nxtmCopnDt: String? = null, // 차기이표일자 (옵션)
    val rbfCopnDt: String? = null, // 직전이표일자 (옵션)
    val bnkHldyIntPydyDcd: String? = null, // 은행휴일이자지급일구분코드 (옵션)
    val bnkHldyIntPydyDcdNm: String? = null, // 은행휴일이자지급일구분코드명 (옵션)
    val sttrHldyIntPydyDcd: String? = null, // 법정휴일이자지급일구분코드 (옵션)
    val sttrHldyIntPydyDcdNm: String? = null, // 법정휴일이자지급일구분코드명 (옵션)
    val intPayMmntDcd: String? = null, // 이자지급시기구분코드 (옵션)
    val intPayMmntDcdNm: String? = null, // 이자지급시기구분코드명 (옵션)
    val elpsIntPayYn: String? = null, // 경과이자지급여부 (옵션)
    val kisScrsItmsKcd: String? = null, // 한국신용평가유가증권종목종류코드 (옵션)
    val kisScrsItmsKcdNm: String? = null, // 한국신용평가유가증권종목종류코드명 (옵션)
    val kbpScrsItmsKcd: String? = null, // 한국자산평가유가증권종목종류코드 (옵션)
    val kbpScrsItmsKcdNm: String? = null, // 한국자산평가유가증권종목종류코드명 (옵션)
    val niceScrsItmsKcd: String? = null, // NICE평가정보유가증권종목종류코드 (옵션)
    val niceScrsItmsKcdNm: String? = null, // NICE평가정보유가증권종목종류코드명 (옵션)
    val fnScrsItmsKcd: String? = null, // FN유가증권종목종류코드 (옵션)
    val fnScrsItmsKcdNm: String? = null // FN유가증권종목종류코드명 (옵션)
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