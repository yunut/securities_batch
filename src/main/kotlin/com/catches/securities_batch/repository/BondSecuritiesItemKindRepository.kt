package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.BondInterestType
import com.catches.securities_batch.repository.entity.BondSecuritiesItemKind
import org.springframework.data.jpa.repository.JpaRepository

interface BondSecuritiesItemKindRepository: JpaRepository<BondSecuritiesItemKind, Long> {
    fun findBondSecuritiesItemKindByCode(code: String): BondSecuritiesItemKind?
}
