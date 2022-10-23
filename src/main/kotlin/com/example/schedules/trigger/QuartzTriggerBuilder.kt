package com.example.schedules.trigger

import org.quartz.CronScheduleBuilder.dailyAtHourAndMinute
import org.quartz.JobExecutionContext
import org.quartz.SimpleScheduleBuilder.simpleSchedule
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.springframework.stereotype.Component
import java.util.Date

@Component
class QuartzTriggerBuilder {

    /**
     * withIdentity - 작업 또는 트리거의 고유 ID를 설정
     *
     *     ex) JobKey (name) or (name, group)
     *
     * startNow - 현재시간을 트리거 시작시간으로 설정
     *
     * startAt - 트리거 시작일을 설정
     *
     * endAt - 트리거 종료일 설정
     *
     * withPriority - 트리거 실행 및 복구 우선순위를 설정
     *
     *     default - 5
     *
     *     음수 또는 양수
     *     우선순위는 트리거의 실행 시간이 동일한 경우 비교된다.
     *     10:59에 예약된 트리거는 11:00에 예약된 트리거보다 먼저 실행된다.
     *
     * withSchedule - 일정을 설정
     *
     *     simpleSchedule - 지정한 시간에 작업, 지연, N번 반복해야 하는 경우 사용
     *     CornSchedule - '매주 금요일, 정오' 등 달력과 같은 일정에 따라 트리거되는 경우 사용
     *
     *          withInterval... - 실행되는 간격
     *          withRepeatCount - 실행 횟수 설정
     *
     *              ex) 0 = 1회 실행
     *
     *          repeatForever - 무한 반복 설정
     *          withMisfireHandlingInstruction... - 불발시 대처
     *
     * usingJobData - [JobDataMap][JobExecutionContext.getMergedJobDataMap]에 데이터를 적재
     */
    fun newTrigger(name: String, startAt: Date, endAt: Date): Trigger {
        return TriggerBuilder.newTrigger()
            .startNow()
            .withIdentity(name, GROUP)
            .startAt(startAt)
            .endAt(endAt)
            .withPriority(PRIORITY)
            .withSchedule(dailyAtHourAndMinute(9, 30))
            .build()
    }

    fun newTrigger(name: String): Trigger {
        return TriggerBuilder.newTrigger()
            .withIdentity(name, GROUP)
            .startNow()
            .endAt(Date(System.currentTimeMillis() + EXECUTE_TIME))
            .withSchedule(
                simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever()
            )
            .build()
    }

    companion object {
        const val GROUP = "TRIGGER_GROUP"
        const val PRIORITY = 1

        const val EXECUTE_TIME = 60000
    }

}