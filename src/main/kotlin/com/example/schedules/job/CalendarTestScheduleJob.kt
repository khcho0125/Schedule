package com.example.schedules.job

import org.quartz.Job
import org.quartz.JobExecutionContext
import java.time.LocalDateTime

class CalendarTestScheduleJob : Job {

    override fun execute(context: JobExecutionContext?) {
        println("[CalendarTestScheduleJob] ${LocalDateTime.now()} - execute")
    }
}