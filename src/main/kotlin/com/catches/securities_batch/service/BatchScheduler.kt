package com.catches.securities_batch.service

import org.springframework.batch.core.*
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class BatchScheduler(
    private val jobLauncher: JobLauncher,
    private val bondInformationJob: Job,
    private val bondPriceJob: Job,
    private val bondGradeRankJob: Job
) {
//    @Scheduled(cron = "0 0 2 * * ?")  // 매일 새벽 2시에 실행
    fun runBondInformationBatchJob() {
        executeBatchJob(bondInformationJob)
    }

//    @Scheduled(cron = "0 0 3 * * ?")
    @Scheduled(fixedDelay = 100000)
    fun runBondPriceBatchJob() {
        executeBatchJob(bondPriceJob)
    }

//    @Scheduled(cron = "0 0 4 * * ?")
    fun runBondGradeRankBatchJob() {
        executeBatchJob(bondGradeRankJob)
    }

    private fun executeBatchJob(job: Job) {
        val jobParameters = JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters()

        try {
            val jobExecution: JobExecution = jobLauncher.run(job, jobParameters)
            println("Job Execution Status: ${jobExecution.status}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}