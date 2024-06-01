package com.catches.securities_batch.repository.dto

import jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime


@Entity
@Table(name = "bond")
class Bond(
    @Id
    @Column(name = "isin_code", length = 12)
    var isinCode: String? = null,

    @Column(name = "isin_code_name", length = 200)
    var isinCodeName: String? = null,

    @Column(name = "crno", length = 13, nullable = false)
    var crno: String,

    @Column(name = "issuer_name", length = 200, nullable = false)
    var issuerName: String,

    @Column(name = "issue_date", nullable = false)
    var issueDate: LocalDate,

    @Column(name = "securities_item_kind_code", length = 4, nullable = false)
    var securitiesItemKindCode: String? = null,

    @Column(name = "issue_format_name", length = 100, nullable = false)
    var issueFormatName: String? = null,

    @Column(name = "expired_date", nullable = false)
    var expiredDate: LocalDate? = null,

    @Column(name = "surface_interest_rate", precision = 15, scale = 10, nullable = false)
    var surfaceInterestRate: BigDecimal? = null,

    @Column(name = "issue_currency_code", length = 3, nullable = false)
    var issueCurrencyCode: String? = null,

    @Column(name = "interest_change_code", length = 1, nullable = false)
    var interestChangeCode: String? = null,

    @Column(name = "interest_type_code", length = 1, nullable = false)
    var interestTypeCode: String? = null,

    @Column(name = "price", precision = 15, scale = 2, nullable = false)
    var price: BigDecimal? = null,

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    constructor(
        crno: String,
        issuerName: String,
        issueDate: LocalDate,
        securitiesItemKindCode: String?,
        isinCode: String?,
        isinCodeName: String?,
        issueFormatName: String?,
        expiredDate: LocalDate?,
        issueCurrencyCode: String?,
        surfaceInterestRate: BigDecimal?,
        interestChangeCode: String?,
        interestTypeCode: String?,
        price: BigDecimal?
    ) : this(
        crno = crno,
        issuerName = issuerName,
        issueDate = issueDate,
        securitiesItemKindCode = securitiesItemKindCode,
        isinCode = isinCode,
        isinCodeName = isinCodeName,
        issueFormatName = issueFormatName,
        expiredDate = expiredDate,
        issueCurrencyCode = issueCurrencyCode,
        surfaceInterestRate = surfaceInterestRate,
        interestChangeCode = interestChangeCode,
        interestTypeCode = interestTypeCode,
    ){
        this.price = price
    }
}
