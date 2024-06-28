package com.catches.securities_batch.batch

import com.catches.securities_batch.repository.BondRepository
import com.catches.securities_batch.repository.entity.BondGradeRank
import org.springframework.batch.item.ItemReader

class BondGradeRankItemReader(
    private val bondRepository: BondRepository
) : ItemReader<BondGradeRank> {
    private var nextBondIndex: Int = 0
    private val bondList = bondRepository.findBondFetchJoin()
        .groupBy { it.issuerCode.grade }
        .flatMap { (_, bonds) ->
            bonds.sortedByDescending { it.surfaceInterestRate }
                .take(3)
                .mapIndexed { index, bond ->
                    BondGradeRank(
                        grade = bond.issuerCode.grade!!,
                        ranking = index + 1,
                        isinCode = bond.isinCode,
                        isinCodeName = bond.isinCodeName,
                        surfaceInterestRate = bond.surfaceInterestRate,
                        expiredDate = bond.expiredDate
                    )
                }
        }

    override fun read(): BondGradeRank? {
        return if (nextBondIndex < bondList.size) {
            bondList[nextBondIndex++]
        } else {
            null
        }
    }
}