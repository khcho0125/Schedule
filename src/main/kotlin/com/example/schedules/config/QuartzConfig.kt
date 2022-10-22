package com.example.schedules.config

import com.example.schedules.schedule.QuartzSchedule
import org.quartz.JobBuilder.newJob
import org.quartz.SimpleScheduleBuilder.simpleSchedule
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.impl.StdSchedulerFactory
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class QuartzConfig {


    /**
     * schedule - 스케줄러와 상호작용하기 위한 API
     *
     * Job - 스케줄러에 의해 실행되기 원하는 작업의 세부 정보를 정의
     */
    @PostConstruct
    fun setUpJob() {
        val scheduler = StdSchedulerFactory().scheduler

        scheduler.start()

        val job = newJob(QuartzSchedule::class.java)
            .withIdentity("QUARTZ_JOB", "GROUP")
            .build()

        val trigger = newTrigger()
            .withIdentity("QUARTZ_TRIGGER", "GROUP")
            .startNow()
            .withSchedule(
                simpleSchedule()
                    .withIntervalInSeconds(10)
                    .withRepeatCount(5)
            ).build()

        scheduler.scheduleJob(job, trigger)
    }

}