package com.catches.securities_batch.repository.dto

import jakarta.persistence.*

@Entity
@Table(name = "bond_interest_change")
class BondInterestChange(
    @Id
    @Column(name = "code", length = 1, nullable = false)
    var code: String? = null,

    @Column(name = "name", length = 100)
    var name: String? = null
)
