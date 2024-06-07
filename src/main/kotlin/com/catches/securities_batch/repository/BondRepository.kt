package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.entity.Bond
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface BondRepository: JpaRepository<Bond, Long> {
    fun findBondByIsinCode(isinCode: String): Bond?
    @Modifying
    @Transactional
    @Query("UPDATE Bond b SET b.price = :price, b.pricedDate = :pricedDate WHERE b.isinCode = :isinCode")
    fun updateBondPrice(isinCode: String, price: Double, pricedDate: LocalDate)
}
