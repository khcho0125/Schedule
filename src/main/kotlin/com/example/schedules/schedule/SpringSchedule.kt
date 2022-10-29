package com.example.schedules.schedule

import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.LocalTime
import java.util.concurrent.TimeUnit

/**
 * **Scheduling을 활성화하는 어노테이션**
 *
 * config 클래스에 명시할 수도 있고
 * SpringBootApplication에 명시해도 된다.
 */
@EnableScheduling
@Component
class SpringSchedule {

    /**
     * **Schedule을 생성하는 어노테이션**
     *
     *
     * [fixedDelay][Scheduled.fixedDelay] - 작업이 끝난 시점부터 시간을 카운트한다.
     *
     *
     * [fixedRate][Scheduled.fixedRate] - 작업의 시작부터 시간을 카운트한다.
     *
     *
     * [cron][Scheduled.cron] - 크론 표현식을 사용한다.
     *
     *     초 - 0 ~ 59
     *
     *     분 - 0 ~ 59
     *
     *     시 - 0 ~ 23
     *
     *     일 - 1 ~ 31
     *
     *     월 - 1 ~ 12
     *
     *     요일 - 0 ~ 6 (Sunday ~ Saturday)
     *
     *     년 - 생략 가능
     *
     *
     * [initialDelay][Scheduled.initialDelay] - 초기 실행 딜레이
     *
     *
     * [zone][Scheduled.zone] - 시간대 변경
     *
     *    default - server local
     *
     *
     * [timeUnit][Scheduled.timeUnit] - 단위 변경
     *
     *    default - millisecond
      */
    @Scheduled(
        timeUnit = TimeUnit.SECONDS,
        fixedDelayString = "\${fixedDelay.second}",
        initialDelayString = "\${initialDelay.second}"
    )
    fun schedule() {
        println("${LocalDate.now()} ${LocalTime.now()} [scheduler] - ${System.currentTimeMillis()}")
    }

}