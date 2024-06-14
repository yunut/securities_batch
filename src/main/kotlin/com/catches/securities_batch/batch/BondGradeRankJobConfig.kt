package com.catches.securities_batch.batch

import com.catches.securities_batch.repository.BondGradeRankRepository
import com.catches.securities_batch.repository.BondRepository
import com.catches.securities_batch.repository.entity.BondGradeRank
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
class BondGradeRankJobConfig(
    private val bondRepository: BondRepository,
    private val bondGradeRankRepository: BondGradeRankRepository
): DefaultBatchConfiguration() {
    val chunkSize = 10

    @Bean
    fun bondGradeRankJob(jobRepository: JobRepository, @Qualifier("bondGradeRankStep") step: Step): Job {
        return JobBuilder("bondGradeRankJob", jobRepository)
            .incrementer(RunIdIncrementer())
            .start(step)
            .build()
    }

    @Bean
    fun bondGradeRankStep(jobRepository: JobRepository, transactionManager: PlatformTransactionManager): Step {
        return StepBuilder("bondGradeRankStep", jobRepository)
            .chunk<BondGradeRank, BondGradeRank>(chunkSize, transactionManager)
            .reader(bondGradeRankReader())
            .writer(bondGradeRankWriter())
            .build()
    }

    @Bean
    @StepScope
    fun bondGradeRankReader(): ItemReader<BondGradeRank> {
        return BondGradeRankItemReader(bondRepository)
    }

    @Bean
    @StepScope
    fun bondGradeRankWriter(): ItemWriter<BondGradeRank> {
        return BondGradeRankItemWriter(bondGradeRankRepository)
    }
}