package com.catches.securities_batch.service

import com.catches.securities_batch.http.dto.BondItem
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import com.catches.securities_batch.repository.*
import com.catches.securities_batch.repository.dto.*
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
    private val bondSecuritiesItemKindRepository: BondSecuritiesItemKindRepository
) {
    fun getBondInformation(pageNo: Int, rows: Int) {
        val temp = dataGoKrApiInterface.getBondInformation(
            serviceKey = "l8DcvgGgm77mgVdHP4xMbgBY6GigF+EPEzhFwpNgFOZ7kZkrUtxbcMeBEkJmpLpSpDbnaiRVi/RfhTZwsp1OQg==",
            pageNo = pageNo,
            numOfRows = rows,
            resultType = "json",
            basDt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
        ).execute().body()

        println(temp?.response?.body?.items?.item?.size)

        temp?.response?.body?.items?.item?.forEach {
            bondIssuerRepository.save(BondIssuer(name = it.bondIsurNm, crno = it.crno))
            bondInterestChangeRepository.save(BondInterestChange(code = it.irtChngDcd, name = it.irtChngDcdNm))
            bondInterestTypeRepository.save(BondInterestType(code = it.bondIntTcd, name = it.bondIntTcdNm))
            bondSecuritiesItemKindRepository.save(BondSecuritiesItemKind(code = it.scrsItmsKcd, name = it.scrsItmsKcdNm))
            saveBondInformation(it)
        }
    }

    @Transactional
    fun saveBondInformation(bondItem: BondItem) {
        val bondInformation = bondItem.toBond()
        bondRepository.save(bondInformation)
    }

    fun BondItem.toBond(): Bond {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

        return Bond(
            crno = this.crno,
            issuerName = this.bondIsurNm,
            issueDate = LocalDate.parse(this.bondIssuDt, formatter),
            securitiesItemKindCode = this.scrsItmsKcd,
            isinCode = this.isinCd,
            isinCodeName = this.isinCdNm,
            issueFormatName = this.bondIssuFrmtNm,
            expiredDate = LocalDate.parse(this.bondExprDt, formatter),
            issueCurrencyCode = this.bondIssuCurCd,
            surfaceInterestRate = BigDecimal(this.bondSrfcInrt),
            interestChangeCode = this.irtChngDcd,
            interestTypeCode = this.bondIntTcd,
            price = null
        )
    }
}
