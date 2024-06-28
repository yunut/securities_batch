package com.catches.securities_batch.repository.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "bond_price_history", uniqueConstraints = [
    UniqueConstraint(columnNames = ["code", "priced_date"])
])
class BondPriceHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "code", length = 13, nullable = false)
    var code: String,

    @Column(name = "price", precision = 15, scale = 2, nullable = false)
    var price: BigDecimal,

    @Column(name = "priced_date", nullable = false)
    var pricedDate: LocalDate,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)