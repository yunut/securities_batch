package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.dto.BondInterestChange
import org.springframework.data.jpa.repository.JpaRepository

interface BondInterestChangeRepository: JpaRepository<BondInterestChange, Long> {
}
