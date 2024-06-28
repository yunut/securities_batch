package com.catches.securities_batch.service

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import com.catches.securities_batch.properties.HttpProperty
import com.catches.securities_batch.repository.*
import com.catches.securities_batch.repository.entity.*
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

@Service
class BondService(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
    private val bondRepository: BondRepository,
    private val bondIssuerRepository: BondIssuerRepository,
    private val bondInterestChangeRepository: BondInterestChangeRepository,
    private val bondInterestTypeRepository: BondInterestTypeRepository,
    private val bondSecuritiesItemKindRepository: BondSecuritiesItemKindRepository,
    private val bondPriceHistoryRepository: BondPriceHistoryRepository,
    private val bondOptionTypeRepository: BondOptionTypeRepository,
    private val httpProperty: HttpProperty
) {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    fun generateRandom12DigitNumber(): String {
        val randomNumber = Random.nextLong(100_000_000_000L, 999_999_999_999L).toString().padStart(12, '0')  // 12자리로 보장
        return "N$randomNumber"
    }

    @Transactional
    fun saveBondPriceHistory(bondPriceHistoryList: List<BondPriceHistory>) {
        bondPriceHistoryList.forEach {
            try {
                bondPriceHistoryRepository.save(it)
            } catch (e: DataIntegrityViolationException) {
                println("Data integrity violation for code: ${it.code} and date: ${it.pricedDate}")
            } catch (e: Exception) {
                println("Error upserting bond price history for code: ${it.code} and date: ${it.pricedDate}: ${e.message}")
            }
        }

    }

    fun saveBondInformation(bondItem: BondInformationDto): Bond {
        val crno = if(bondItem.crno == "") generateRandom12DigitNumber() else bondItem.crno
        val issuer = bondIssuerRepository.findBondIssuerByName(bondItem.bondIsurNm)
            ?: BondIssuer(crno, bondItem.bondIsurNm, null).apply {
                bondIssuerRepository.save(this)
            }

        val irtChngDcdNm = bondItem.irtChngDcdNm!!.split("-")[0]
        val change = bondInterestChangeRepository.findBondInterestChangeByName(irtChngDcdNm)
            ?: BondInterestChange(bondItem.irtChngDcd!!, irtChngDcdNm).apply {
                bondInterestChangeRepository.save(this)
            }
        val type = bondInterestTypeRepository.findBondInterestTypeByCode(bondItem.bondIntTcd!!)
            ?: BondInterestType(bondItem.bondIntTcd, bondItem.bondIntTcdNm).apply {
                bondInterestTypeRepository.save(this)
            }
        val kind = bondSecuritiesItemKindRepository.findBondSecuritiesItemKindByCode(bondItem.scrsItmsKcd!!)
            ?: BondSecuritiesItemKind(bondItem.scrsItmsKcd, bondItem.scrsItmsKcdNm).apply {
                bondSecuritiesItemKindRepository.save(this)
            }
        val optionType = bondItem.optnTcdNm?.let { optnTcdNm ->
            bondOptionTypeRepository.findBondOptionTypeByName(optnTcdNm)
                ?: BondOptionType(bondItem.optnTcd, optnTcdNm).apply {
                    bondOptionTypeRepository.save(this)
                }
        }

        return Bond.toBond(
            issuer = issuer,
            issueDate = LocalDate.parse(bondItem.bondIssuDt, formatter),
            securitiesItemKind = kind,
            isinCode = bondItem.isinCd!!,
            isinCodeName = bondItem.isinCdNm!!.replace(" ", ""),
            expiredDate = LocalDate.parse(bondItem.bondExprDt, formatter),
            issueCurrencyCode = bondItem.bondIssuCurCd!!,
            surfaceInterestRate = bondItem.bondSrfcInrt!!.toBigDecimal(),
            interestChange = change,
            interestType = type,
            interestPaymentCycle = bondItem.intPayCyclCtt,
            optionType = optionType
        )
    }
}
