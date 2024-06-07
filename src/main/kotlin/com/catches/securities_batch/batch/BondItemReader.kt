package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
//import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import org.springframework.batch.item.ItemReader
import org.springframework.beans.factory.annotation.Qualifier
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BondItemReader(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
) : ItemReader<BondInformationDto> {

    private var bondList: List<BondInformationDto>? = null
    private var nextBondIndex: Int = 0

    override fun read(): BondInformationDto? {
        if (bondList == null) {
            val rows = dataGoKrApiInterface.getBondInformation(
                serviceKey = "l8DcvgGgm77mgVdHP4xMbgBY6GigF+EPEzhFwpNgFOZ7kZkrUtxbcMeBEkJmpLpSpDbnaiRVi/RfhTZwsp1OQg==",
                pageNo = 1,
                numOfRows = 1,
                resultType = "json",
                basDt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            ).execute().body()?.response?.body?.totalCount ?: 10000

            bondList = dataGoKrApiInterface.getBondInformation(
                serviceKey = "l8DcvgGgm77mgVdHP4xMbgBY6GigF+EPEzhFwpNgFOZ7kZkrUtxbcMeBEkJmpLpSpDbnaiRVi/RfhTZwsp1OQg==",
                pageNo = 1,
                numOfRows = 1000,
                resultType = "json",
                basDt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            ).execute().body()?.response?.body?.items?.item
        }

        return if (nextBondIndex < bondList!!.size) {
            bondList!![nextBondIndex++]
        } else {
            null
        }
    }
}