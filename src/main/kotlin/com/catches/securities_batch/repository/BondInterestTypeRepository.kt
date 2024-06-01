package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.dto.BondInterestType
import org.springframework.data.jpa.repository.JpaRepository

interface BondInterestTypeRepository: JpaRepository<BondInterestType, Long> {
}
