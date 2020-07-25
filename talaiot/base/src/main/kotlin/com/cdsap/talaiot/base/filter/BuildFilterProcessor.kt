package com.cdsap.talaiot.base.filter

import com.cdsap.talaiot.base.configuration.BuildFilterConfiguration
import com.cdsap.talaiot.base.entities.ExecutionReport
import com.cdsap.talaiot.base.logger.LogTracker

class BuildFilterProcessor(
    val logTracker: LogTracker,
    val filter: BuildFilterConfiguration
) {

    fun shouldPublishBuild(report: ExecutionReport): Boolean {
        val successAllowsPublishing = report.success == filter.success || filter.success == null
        return if (successAllowsPublishing) {
            val processor =
                StringFilterProcessor(filter.requestedTasks, logTracker)
            return report.requestedTasks?.split(" ")?.any {
                processor.matches(it)
            } ?: true
        } else {
            false
        }
    }
}
