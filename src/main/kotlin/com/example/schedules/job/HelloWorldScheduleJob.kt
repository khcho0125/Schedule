package com.example.schedules.job

import org.quartz.Job
import org.quartz.JobExecutionContext
import java.time.LocalDateTime

class HelloWorldScheduleJob : Job {

    /**
     * JobExecutionContext - 해당 작업의 실행환경에 접근할 수 있는 기능을 정의한 작업의 컨텍스트
     *
     * [fireTime][JobExecutionContext.getFireTime] - 현재 작업의 시작 시간
     *
     * [mergedJobDataMap][JobExecutionContext.getMergedJobDataMap] - JobDataMap에 적재된 데이터를 불러옴
     *
      */
    override fun execute(context: JobExecutionContext?) {

        if (context == null) {
            println("JobExecuteContext haven't data")
            return
        }

        println("[HelloWorldScheduleJob] ${LocalDateTime.now()} - execute: ${context.mergedJobDataMap["Hello"]}")
    }


}