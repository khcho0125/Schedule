package com.example.schedules.api

import com.example.schedules.calender.QuartzCalendarBuilder
import com.example.schedules.job.CalendarTestScheduleJob
import org.quartz.JobBuilder.newJob
import org.quartz.SimpleScheduleBuilder.simpleSchedule
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.impl.StdSchedulerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
class CalendarTestScheduler(
    private val quartzCalendarBuilder: QuartzCalendarBuilder
) {

    @PostMapping("/calendar")
    fun setCalendar() {
        val calName = "Test Calendar"
        quartzCalendarBuilder.newHolidayCalendar(calName, Date()) // 오늘 실행 X

        val job = newJob()
            .ofType(CalendarTestScheduleJob::class.java)
            .withIdentity("Test Job")
            .build()

        val trigger = newTrigger()
            .withIdentity("Test Trigger")
            .forJob("Test Job")
            .startNow()
            .withSchedule(
                simpleSchedule()
                    .withIntervalInHours(1)
                    .repeatForever()
            )
            .modifiedByCalendar(calName)
            .build()

        StdSchedulerFactory().scheduler.also {
            it.start()
            it.scheduleJob(job, trigger)
        }

    }

}