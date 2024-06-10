package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.repository.BondRepository
import com.catches.securities_batch.service.BondService
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class BondItemWriter(
    private val bondService: BondService,
    private val bondRepository: BondRepository
) : ItemWriter<BondInformationDto> {
    override fun write(chunk: Chunk<out BondInformationDto>) {
        try {
            chunk.items.forEach {
                val bond = bondRepository.findBondByIsinCode(it.isinCd)
                bond ?: bondService.saveBondInformation(it)
            }
        } catch (e: Exception) {
            //TODO 채권 발행정보 write 이상시 처리
        }


    }
}