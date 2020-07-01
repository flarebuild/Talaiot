package com.cdsap.base.publisher.filter

import com.cdsap.base.publisher.configuration.BuildFilterConfiguration
import com.cdsap.base.publisher.entities.ExecutionReport
import com.cdsap.base.publisher.logger.LogTracker

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
