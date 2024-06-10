package com.catches.securities_batch.service

import org.springframework.batch.core.*
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

@Component
class BatchScheduler(
    private val jobLauncher: JobLauncher,
    private val bondInformationJob: Job
) {
    @Scheduled(cron = "0 0 2 * * ?")  // 매일 새벽 2시에 실행
    fun runBatchJob() {
        val jobParameters = JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .toJobParameters()

        try {
            val jobExecution = jobLauncher.run(bondInformationJob, jobParameters)
            println("Job Execution Status: ${jobExecution.status}")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}