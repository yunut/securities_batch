package com.catches.securities_batch.repository

import com.catches.securities_batch.repository.dto.Bond
import org.springframework.data.jpa.repository.JpaRepository

interface BondRepository: JpaRepository<Bond, Long> {
}
