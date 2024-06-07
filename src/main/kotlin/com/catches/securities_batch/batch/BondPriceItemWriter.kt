package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.dto.BondPriceDto
import com.catches.securities_batch.repository.BondRepository
import com.catches.securities_batch.service.BondService
//import com.catches.securities_batch.repository.BondRepository
//import com.catches.securities_batch.service.BondService
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class BondPriceItemWriter(
    private val bondRepository: BondRepository
) : ItemWriter<BondPriceDto> {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    override fun write(chunk: Chunk<out BondPriceDto>) {
        try {
            chunk.items.forEach {
                val bond = bondRepository.findBondByIsinCode(it.isinCd)
                val pricedDate = LocalDate.parse(it.basDt, formatter)

                bond?.let { item ->
                    if (item.pricedDate == null || item.pricedDate!!.isBefore(pricedDate)) {
                        bondRepository.updateBondPrice(isinCode = it.isinCd, price = it.clprPrc, pricedDate = pricedDate)
                    }
                }
            }
        } catch (e: Exception) {
            println(e.stackTrace)
        }
    }
}