package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondPriceDto
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
import com.catches.securities_batch.properties.HttpProperty
import com.catches.securities_batch.repository.BondPriceHistoryRepository
import com.catches.securities_batch.repository.BondRepository
import com.catches.securities_batch.service.BondService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.*
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@EnableBatchProcessing
class BondPriceJobConfig(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
    private val bondService: BondService,
    private val bondRepository: BondRepository,
    private val bondPriceHistoryRepository: BondPriceHistoryRepository,
    private val httpProperty: HttpProperty,
): DefaultBatchConfiguration() {
    val chunkSize = 10

    @Bean
    fun bondPriceJob(jobRepository: JobRepository, @Qualifier("bondPriceStep") step: Step): Job {
        return JobBuilder("bondPriceJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(step)
            .build()
    }

    @Bean
    fun bondPriceStep(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("bondPriceStep", jobRepository)
            .chunk<BondPriceDto, BondPriceDto>(chunkSize, transactionManager)
            .reader(bondPriceReader())
            .writer(bondPriceWriter())
            .build()
    }

    @Bean
    @StepScope
    fun bondPriceReader(): ItemReader<BondPriceDto> {
        return BondPriceItemReader(dataGoKrApiInterface, httpProperty)
    }

    @Bean
    @StepScope
    fun bondPriceWriter(): ItemWriter<BondPriceDto> {
        return BondPriceItemWriter(bondService, bondRepository, bondPriceHistoryRepository)
    }
}