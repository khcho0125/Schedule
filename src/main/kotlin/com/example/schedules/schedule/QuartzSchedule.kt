package com.example.schedules.schedule

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException

class QuartzSchedule : Job {
    /**
     *
     *
     * Called by the `[Scheduler]` when a `[Trigger]`
     * fires that is associated with the `Job`.
     *
     *
     *
     *
     * The implementation may wish to set a
     * [result][JobExecutionContext.setResult] object on the
     * [JobExecutionContext] before this method exits.  The result itself
     * is meaningless to Quartz, but may be informative to
     * `[JobListener]s` or
     * `[TriggerListener]s` that are watching the job's
     * execution.
     *
     *
     * @throws JobExecutionException
     * if there is an exception while executing the job.
     */
    override fun execute(context: JobExecutionContext?) {


        println("${System.currentTimeMillis()}")
    }
}