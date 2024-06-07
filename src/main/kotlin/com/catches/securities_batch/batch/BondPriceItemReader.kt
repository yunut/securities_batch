package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.dto.BondPriceDto
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import com.catches.securities_batch.properties.HttpProperty
//import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.annotation.Qualifier
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BondPriceItemReader(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
    private val httpProperty: HttpProperty
) : ItemReader<BondPriceDto> {

    private var bondPriceList: List<BondPriceDto>? = null
    private var nextBondIndex: Int = 0

    override fun read(): BondPriceDto? {
        if (bondPriceList == null) {
            val rows = dataGoKrApiInterface.getBondPrice(
                serviceKey = httpProperty.dataGoKr.key,
                pageNo = 1,
                numOfRows = 1,
                resultType = "json",
                beginBasDt = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            ).execute().body()?.response?.body?.totalCount ?: 10000

            bondPriceList = dataGoKrApiInterface.getBondPrice(
                serviceKey = httpProperty.dataGoKr.key,
                pageNo = 1,
                numOfRows = rows,
                resultType = "json",
                beginBasDt = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            ).execute().body()?.response?.body?.items?.item
        }

        return if (nextBondIndex < bondPriceList!!.size) {
            bondPriceList!![nextBondIndex++]
        } else {
            null
        }
    }
}