package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.dto.BondSecuritiesItemKind
import org.springframework.data.jpa.repository.JpaRepository

interface BondSecuritiesItemKindRepository: JpaRepository<BondSecuritiesItemKind, Long> {
}
