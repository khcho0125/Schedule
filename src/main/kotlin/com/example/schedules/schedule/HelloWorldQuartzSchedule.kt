package com.example.schedules.schedule

import com.example.schedules.job.QuartzJobBuilder
import com.example.schedules.trigger.QuartzTriggerBuilder
import org.quartz.impl.StdSchedulerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldQuartzSchedule(
    val quartzJobBuilder: QuartzJobBuilder,
    val quartzTriggerBuilder: QuartzTriggerBuilder
) {

    @PostMapping("/hello")
    fun helloWorld() {
        val jobDetail = quartzJobBuilder.newJob("Hello")
        val trigger = quartzTriggerBuilder.newTrigger("World")

        val scheduler = StdSchedulerFactory().scheduler

        scheduler.start()

        scheduler.scheduleJob(jobDetail, trigger)
    }

}