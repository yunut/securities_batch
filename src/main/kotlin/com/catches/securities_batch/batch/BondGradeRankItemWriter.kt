package com.catches.securities_batch.batch

import com.catches.securities_batch.repository.BondGradeRankRepository
import com.catches.securities_batch.repository.entity.BondGradeRank
import org.springframework.batch.item.Chunk
import org.springframework.batch.item.ItemWriter
import org.springframework.stereotype.Component

@Component
class BondGradeRankItemWriter(
    private val bondGradeRankRepository: BondGradeRankRepository
) : ItemWriter<BondGradeRank> {
    override fun write(chunk: Chunk<out BondGradeRank>) {
        try {
            chunk.items.forEach {
                bondGradeRankRepository.save(it)
            }
        } catch (e: Exception) {
            println(e.stackTrace)
            //TODO 채권 발행정보 write 이상시 처리
        }
    }
}