package com.catches.securities_batch.batch

import com.catches.securities_batch.http.dto.BondInformationDto
import com.catches.securities_batch.http.`interface`.DataGoKrApiInterface
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
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.transaction.PlatformTransactionManager

@Configuration
@EnableBatchProcessing
class BondInformationJobConfig(
    @Qualifier("DataGoKrApiInterface") private val dataGoKrApiInterface: DataGoKrApiInterface,
    private val bondService: BondService,
    private val bondRepository: BondRepository
): DefaultBatchConfiguration() {
    val chunkSize = 10

    @Bean
    fun bondInformationJob(jobRepository: JobRepository, @Autowired step: Step): Job {
        return JobBuilder("bondInformationJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(step)
            .build()
    }

    @Bean
    fun bondInformationStep(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("bondInformationStep", jobRepository)
            .chunk<BondInformationDto, BondInformationDto>(chunkSize, transactionManager)
            .reader(itemReader())
            .writer(itemWriter())
            .build()
    }

    @Bean
    @StepScope
    fun itemReader(): ItemReader<BondInformationDto> {
        return BondItemReader(dataGoKrApiInterface)
    }

    @Bean
    @StepScope
    fun itemWriter(): ItemWriter<BondInformationDto> {
        return BondItemWriter(bondService, bondRepository)
    }
}