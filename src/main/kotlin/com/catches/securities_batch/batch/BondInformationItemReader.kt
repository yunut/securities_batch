package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import com.catches.securities_batch.properties.HttpProperty
import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.annotation.Qualifier
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BondInformationItemReader(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
    private val httpProperty: HttpProperty
) : ItemReader<BondInformationDto> {

    private var bondList: List<BondInformationDto>? = null
    private var nextBondIndex: Int = 0

    override fun read(): BondInformationDto? {
        if (bondList == null) {
            val rows = dataGoKrApiInterface.getBondInformation(
                serviceKey = httpProperty.dataGoKr.key,
                pageNo = 1,
                numOfRows = 1,
                resultType = "json",
                basDt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            ).execute().body()?.response?.body?.totalCount ?: 10000

            bondList = dataGoKrApiInterface.getBondInformation(
                serviceKey = httpProperty.dataGoKr.key,
                pageNo = 1,
                numOfRows = rows,
                resultType = "json",
                basDt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            ).execute().body()?.response?.body?.items?.item?.filter {
                it.crno != "0000000000000" && it.bondOffrMcd != "21" && it.bondOffrMcd != "22"
            }
        }

        return if (nextBondIndex < bondList!!.size) {
            bondList!![nextBondIndex++]
        } else {
            null
        }
    }
}