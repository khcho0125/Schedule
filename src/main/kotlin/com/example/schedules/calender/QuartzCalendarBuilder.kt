package com.example.schedules.calender

import org.quartz.impl.StdSchedulerFactory
import org.quartz.Scheduler
import org.quartz.impl.calendar.HolidayCalendar
import org.springframework.stereotype.Component
import java.util.Date

@Component
class QuartzCalendarBuilder {

    /**
     * Calendar - 일정 달력을 정의
     *
     * excludedDates - 해당 날짜의 일정을 달력에서 제외
     *
     * [addCalendar][Scheduler.addCalendar] - 달력을 추가
     *
     *     calName - 달력의 이름
     *
     *     calendar - 저장할 캘린더 구현체
     *
     *     replace - 동일한 이름, 그룹을 가진 캘린더 덮어쓰기 여부
     *
     *     updateTriggers - 이름이 동일한 캘린더를 참조하는 모든 트리거의
     *                      다음 시작 시간을 새 캘린더로 다시 계산 여부
     */
    fun newHolidayCalendar(calName: String, vararg excludeDays: Date) {
        val scheduler = StdSchedulerFactory().scheduler

        val calendar = HolidayCalendar().also {
            excludeDays.forEach(it::addExcludedDate)
        }

        scheduler.addCalendar(calName, calendar, true, true)
    }

}