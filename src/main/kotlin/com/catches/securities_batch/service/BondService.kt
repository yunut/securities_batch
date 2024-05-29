package com.catches.securities_batch.service

import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class BondService(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
) {
    fun getBondInformation() {
        val temp = dataGoKrApiInterface.getBondInformation(
            serviceKey = "l8DcvgGgm77mgVdHP4xMbgBY6GigF+EPEzhFwpNgFOZ7kZkrUtxbcMeBEkJmpLpSpDbnaiRVi/RfhTZwsp1OQg==",
            pageNo = 1,
            numOfRows = 1,
            resultType = "json",
        ).execute()
    }
}
