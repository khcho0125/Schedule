package com.example.schedules.schedule

import com.example.schedules.job.QuartzScheduleJob
import com.example.schedules.trigger.QuartzTriggerBuilder
import com.example.schedules.job.QuartzJobBuilder
import org.quartz.JobBuilder.newJob
import org.quartz.SimpleScheduleBuilder.simpleSchedule
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.impl.StdSchedulerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class QuartzSchedule {


    /**
     * **일정 요소**
     *
     * * schedule - 스케줄러와 상호작용하기 위한 API
     *
     *
     * * Job - 스케줄러에 의해 실행되기 원하는 작업의 세부 정보를 정의
     *
     *
     * * JobDetail - 작업을 정의하는데 사용
     *
     *
     * * Trigger - 작업이 실행될 일정을 정의
     *
     *
     * * JobBuilder - 작업을 정의하는 JobDetail을 정의하는데 사용
     *
     *
     * * TriggerBuilder - 트리거를 정의하는데 사용
     */
    @PostConstruct
    fun setUpJob() {
        val scheduler = StdSchedulerFactory().scheduler

        scheduler.start()

        /**
         * JobDetail에 대한 설명 - [QuartzJobBuilder]
         */
        val job = newJob(QuartzScheduleJob::class.java)
            .withIdentity("QUARTZ_JOB", "GROUP")
            .build()


        /**
         * Trigger에 대한 설명 - [QuartzTriggerBuilder]
         */
        val trigger = newTrigger()
            .withIdentity("QUARTZ_TRIGGER", "GROUP")
            .startNow()
            .withSchedule(
                simpleSchedule()
                    .withIntervalInSeconds(10)
                    .withRepeatCount(5)
            ).build()


        // scheduleJob - 트리거를 사용해 작업을 예약
        scheduler.scheduleJob(job, trigger)
    }

}