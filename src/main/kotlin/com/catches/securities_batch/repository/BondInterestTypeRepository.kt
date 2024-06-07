package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.BondInterestChange
import com.catches.securities_batch.repository.entity.BondInterestType
import org.springframework.data.jpa.repository.JpaRepository

interface BondInterestTypeRepository: JpaRepository<BondInterestType, Long> {
    fun findBondInterestTypeByCode(code: String): BondInterestType?
}
