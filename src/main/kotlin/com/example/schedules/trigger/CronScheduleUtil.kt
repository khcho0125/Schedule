package com.example.schedules.trigger

/**
 * Cron Expression
 *
 * '*' - 전체 값
 *
 * ? - 특정 값 없음
 *
 * / - 값의 증분 지정
 *
 *     ex) 0/15 0분부터 시작, 매 15분 마다
 *
 * , - 여러 목록 지정
 *
 * '-' - 범위 지정
 *
 *     ex) MAY-JUL == MAY,JUN,JUL
 *
 * L - 마지막 값
 *
 *     단독 사용시
 *     ex) 1월 ⇒ 31일
 *         2월 ⇒ 28 or 29일
 *         요일 - SAT
 *
 *     ex) 6L or FRIL (매월 마지막 금요일)
 *
 * W - 가장 가까운 평일
 *
 *     ex) 15W (매월 15일에 가장 가까운 평일(월 ~ 금))
 *
 * '#' - N번째 주 지정
 *
 *     ex) SUN#2 (2번째 일요일)
 *
 * 월 문자열 목록
 *
 *      1월 - JAN
 *      2월 - FEB
 *      3월 - MAR
 *      4월 - APR
 *      5월 - MAY
 *      6월 - JUN
 *      7월 - JUL
 *      8월 - AUG
 *      9월 - SEP
 *      10월 - OCT
 *      11월 - NOV
 *      12월 - DEC
 *
 * 요일 문자열 목록
 *
 *      일요일 - SUN
 *      월요일 - MON
 *      화요일 - TUE
 *      수요일 - WED
 *      목요일 - THU
 *      금요일 - FRI
 *      토요일 - SAT
 */
object CronScheduleUtil {

    /**
     * 5분 주기 표현식
     */
    const val FIVE_MINUTES = "0 0/5 * * * ?"

    /**
     * 매주 토,일 11:30, 12:30, 13:30 표현식
     */
    const val HOLIDAY_LUNCH = "0 30 11-13 ? * SAT,SUN"

    /**
     * 매월 10일, 20일 오전 9시 ~ 오후 9시사이에 1시간 주기 표현식
     */
    const val TEN_DAYS_ONE_HOUR_EXPRESSION = "0 0 9-21 10,20 * ?"

}