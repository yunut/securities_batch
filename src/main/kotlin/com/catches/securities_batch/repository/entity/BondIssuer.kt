package com.catches.securities_batch.repository.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "bond_issuer")
class BondIssuer(
    @Id
    @Column(name = "code", length = 13, nullable = false)
    var code: String,

    @Column(name = "name", length = 200, nullable = false)
    var name: String,

    @Column(name = "grade", length = 5)
    var grade: String? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
