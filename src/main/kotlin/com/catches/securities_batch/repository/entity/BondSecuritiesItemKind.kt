package com.catches.securities_batch.repository.entity

import jakarta.persistence.*

@Entity
@Table(name = "bond_securities_item_kind")
class BondSecuritiesItemKind(
    @Id
    @Column(name = "code", length = 4, nullable = false)
    var code: String? = null,

    @Column(name = "name", length = 100)
    var name: String? = null
)
