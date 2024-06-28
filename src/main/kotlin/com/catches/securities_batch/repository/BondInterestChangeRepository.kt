package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.BondInterestChange
import org.springframework.data.jpa.repository.JpaRepository

interface BondInterestChangeRepository: JpaRepository<BondInterestChange, Long> {
    fun findBondInterestChangeByName(name: String): BondInterestChange?
}
