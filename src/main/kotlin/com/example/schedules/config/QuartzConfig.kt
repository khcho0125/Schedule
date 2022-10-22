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
         * newJob - 실행할 Job을 설정
         *
         *     ex) QuartzSchedule.class
         *
         * withIdentity - 작업 또는 트리거의 고유 키를 설정
         *
         *     ex) JobKey (name) or (name, group)
         *
         */
        val job = newJob(QuartzSchedule::class.java)
            .withIdentity("QUARTZ_JOB", "GROUP")
            .build()


        /**
         * withIdentity - 작업 또는 트리거의 고유 키를 설정
         *
         *     ex) JobKey (name) or (name, group)
         *
         * startNow - 현재시간을 시작시간으로 설정
         *
         * withSchedule - 일정을 설정
         * * simpleSchedule - 지정한 시간에 작업, 지연, N번 반복해야 하는 경우 사용
         * * CornSchedule - '매주 금요일, 정오' 등 달력과 같은 일정에 따라 트리거되는 경우 사용
         *
         *    * withInterval... - 실행되는 간격
         *    * withRepeatCount - 실행 횟수 설정
         *    * repeatForever - 무한 반복 설정
         *    * withMisfireHandlingInstruction... - 오작동시 대처
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