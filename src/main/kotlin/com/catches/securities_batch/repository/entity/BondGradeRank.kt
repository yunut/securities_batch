package com.catches.securities_batch.repository.entity

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "bond_grade_rank")
class BondGradeRank(
    @Column(name = "grade", length = 5)
    var grade: String,

    @Column(name = "ranking")
    var ranking: Int,

    @Id
    @Column(name = "isin_code", length = 13, nullable = false)
    var isinCode: String,

    @Column(name = "isin_code_name", length = 100, nullable = false)
    var isinCodeName: String,

    @Column(name = "surface_interest_rate", precision = 15, scale = 10, nullable = false)
    var surfaceInterestRate: BigDecimal,

    @Column(name = "expired_date", nullable = false)
    var expiredDate: LocalDate,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)