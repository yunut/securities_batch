package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.BondOptionType
import org.springframework.data.jpa.repository.JpaRepository

interface BondOptionTypeRepository: JpaRepository<BondOptionType, Long> {
    fun findBondOptionTypeByCode(code: String): BondOptionType?
}
