package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.BondGradeRank
import org.springframework.data.jpa.repository.JpaRepository

interface BondGradeRankRepository: JpaRepository<BondGradeRank, Long> {
}
