package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.BondPriceHistory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import java.time.LocalDate


interface BondPriceHistoryRepository: JpaRepository<BondPriceHistory, Long> {
    @Modifying
    @Transactional
    @Query(
        value = "INSERT INTO bond_price_history (code, price, priced_date) VALUES (:code, :price, :pricedDate) " +
                "ON DUPLICATE KEY UPDATE price = :price, priced_date = :pricedDate", nativeQuery = true
    )
    fun upsertBondPriceHistory(
        @Param("code") code: String?,
        @Param("price") price: BigDecimal?,
        @Param("pricedDate") pricedDate: LocalDate?
    )
}
