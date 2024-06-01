package com.catches.securities_batch.repository.dto

import jakarta.persistence.*

@Entity
@Table(name = "bond_issuer")
class BondIssuer(
    @Id
    @Column(name = "name", length = 200, nullable = false)
    var name: String,

    @Column(name = "crno", length = 13, nullable = false)
    var crno: String
)
