package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.dto.BondPriceDto
import com.catches.securities_batch.repository.BondPriceHistoryRepository
import com.catches.securities_batch.repository.BondRepository
import com.catches.securities_batch.repository.entity.BondPriceHistory
import com.catches.securities_batch.service.BondService
//import com.catches.securities_batch.repository.BondRepository
//import com.catches.securities_batch.service.BondService
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Component
class BondPriceItemWriter(
    private val bondService: BondService,
    private val bondRepository: BondRepository,
    private val bondPriceHistoryRepository: BondPriceHistoryRepository
) : ItemWriter<BondPriceDto> {
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")

    override fun write(chunk: Chunk<out BondPriceDto>) {
        try {
            val bondPriceHistoryList = chunk.items.map {
                BondPriceHistory(
                    code = it.isinCd,
                    price = it.clprPrc.toBigDecimal(),
                    pricedDate = LocalDate.parse(it.basDt, formatter)
                )
            }

//            bondService.saveBondPriceHistory(bondPriceHistoryList)

            bondPriceHistoryList.forEach {
                bondPriceHistoryRepository.upsertBondPriceHistory(it.code, it.price, it.pricedDate)
            }

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