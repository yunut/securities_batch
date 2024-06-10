package com.catches.securities_batch.service

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import com.catches.securities_batch.properties.HttpProperty
import com.catches.securities_batch.repository.*
import com.catches.securities_batch.repository.entity.*
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class BondService(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
    private val bondRepository: BondRepository,
    private val bondIssuerRepository: BondIssuerRepository,
    private val bondInterestChangeRepository: BondInterestChangeRepository,
    private val bondInterestTypeRepository: BondInterestTypeRepository,
    private val bondSecuritiesItemKindRepository: BondSecuritiesItemKindRepository,
    private val httpProperty: HttpProperty
) {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    fun getBondInformation(pageNo: Int, rows: Int) {
        val temp = dataGoKrApiInterface.getBondInformation(
            serviceKey = httpProperty.dataGoKr.key,
            pageNo = pageNo,
            numOfRows = rows,
            resultType = "json",
            basDt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        ).execute().body()

        temp?.response?.body?.items?.item?.forEach {
            val bond = bondRepository.findBondByIsinCode(it.isinCd)
            bond ?: saveBondInformation(it)
        }
    }

    fun getBondPrice(pageNo: Int, rows: Int) {
        val temp = dataGoKrApiInterface.getBondPrice(
            serviceKey = httpProperty.dataGoKr.key,
            pageNo = pageNo,
            numOfRows = rows,
            resultType = "json",
            beginBasDt = "20240101"
        ).execute().body()

        temp?.response?.body?.items?.item?.forEach {
            val bond = bondRepository.findBondByIsinCode(it.isinCd)
            val pricedDate = LocalDate.parse(it.basDt, formatter)

            bond?.let { item ->
                if (item.pricedDate == null || item.pricedDate!!.isBefore(pricedDate)) {
                    bondRepository.updateBondPrice(isinCode = it.isinCd, price = it.clprPrc, pricedDate = pricedDate)
                }
            }
        }
    }

    fun saveBondInformation(bondItem: BondInformationDto) {
        val issuer = bondIssuerRepository.findBondIssuerByName(bondItem.bondIsurNm)
            ?: BondIssuer(bondItem.bondIsurNm, bondItem.crno).apply {
                bondIssuerRepository.save(this)
            }
        val change = bondInterestChangeRepository.findBondInterestChangeByCode(bondItem.irtChngDcd)
            ?: BondInterestChange(bondItem.irtChngDcd, bondItem.irtChngDcdNm).apply {
                bondInterestChangeRepository.save(this)
            }
        val type = bondInterestTypeRepository.findBondInterestTypeByCode(bondItem.bondIntTcd)
            ?: BondInterestType(bondItem.bondIntTcd, bondItem.bondIntTcdNm).apply {
                bondInterestTypeRepository.save(this)
            }
        val kind = bondSecuritiesItemKindRepository.findBondSecuritiesItemKindByCode(bondItem.scrsItmsKcd)
            ?: BondSecuritiesItemKind(bondItem.scrsItmsKcd, bondItem.scrsItmsKcdNm).apply {
                bondSecuritiesItemKindRepository.save(this)
            }

        bondRepository.save(
            Bond.toBond(
                crno = bondItem.crno,
                issuer = issuer,
                issueDate = LocalDate.parse(bondItem.bondIssuDt, formatter),
                securitiesItemKind = kind,
                isinCode = bondItem.isinCd,
                isinCodeName = bondItem.isinCdNm.trim(),
                issueFormatName = bondItem.bondIssuFrmtNm,
                expiredDate = LocalDate.parse(bondItem.bondExprDt, formatter),
                issueCurrencyCode = bondItem.bondIssuCurCd,
                surfaceInterestRate = bondItem.bondSrfcInrt.toBigDecimal(),
                interestChange = change,
                interestType = type
            )
        )
    }
}
