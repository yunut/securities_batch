package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.dto.BondIssuer
import org.springframework.data.jpa.repository.JpaRepository

interface BondIssuerRepository: JpaRepository<BondIssuer, Long> {
}
