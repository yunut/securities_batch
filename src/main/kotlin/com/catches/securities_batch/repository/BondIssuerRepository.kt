package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.Bond
import com.catches.securities_batch.repository.entity.BondIssuer
import org.springframework.data.jpa.repository.JpaRepository

interface BondIssuerRepository: JpaRepository<BondIssuer, Long> {
    fun findBondIssuerByName(name: String): BondIssuer?
}
