package com.example.schedules.job

import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.JobExecutionContext
import org.springframework.stereotype.Component

@Component
class QuartzJobBuilder {

    /**
     * newJob - 실행할 Job을 설정
     *
     *     ex) QuartzSchedule.class
     *
     * withIdentity - 작업 또는 트리거의 고유 ID를 설정
     *
     *     ex) JobKey (name) or (name, group)
     *
     * usingJobData - [JobDataMap][JobExecutionContext.getMergedJobDataMap]에 데이터를 적재
     *
     * storeDurably - Job이 분리된 후에도 Job이 저장된 상태로 있어야 하는지 여부 설정
     *
     *     default - 저장하지 않음(false)
     */
    fun newJob(name: String): JobDetail {

        return JobBuilder.newJob()
            .withIdentity(name, GROUP)
            .usingJobData("Hello", "World")
            .ofType(HelloWorldScheduleJob::class.java)
            .storeDurably()
            .build()
    }

    companion object {
        const val GROUP = "JOB_GROUP"
    }

}