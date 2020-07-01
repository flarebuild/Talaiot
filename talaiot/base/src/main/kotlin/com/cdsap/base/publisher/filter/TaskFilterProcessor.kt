package com.cdsap.base.publisher.filter

import com.cdsap.base.publisher.configuration.FilterConfiguration
import com.cdsap.base.publisher.entities.TaskLength
import com.cdsap.base.publisher.logger.LogTracker
import com.cdsap.base.publisher.configuration.ThresholdConfiguration

class TaskFilterProcessor(
    val logTracker: LogTracker,
    val filter: FilterConfiguration?
) {

    fun taskLengthFilter(taskLength: TaskLength): Boolean {
        var isTaskIncluded = true
        var isModuleIncluded = true
        var thresholdIncluded = true
        filter?.let { filter ->

            filter.modules?.let { moduleFilter ->
                isModuleIncluded = executeFilterProcessor(moduleFilter, taskLength.module)
            }
            filter.tasks?.let { taskFilter ->
                isTaskIncluded = executeFilterProcessor(taskFilter, taskLength.taskName)
            }
            filter.threshold?.let { x ->
                thresholdIncluded = threshold(x, taskLength)
            }

        }
        return isTaskIncluded && isModuleIncluded && thresholdIncluded
    }

    private fun executeFilterProcessor(
        filter: StringFilter, argument: String
    ): Boolean {
        return with(StringFilterProcessor(filter, logTracker)) {
            matches(argument)
        }
    }

    private fun threshold(thresholdConfiguration: ThresholdConfiguration?, task: TaskLength) =
        if (thresholdConfiguration == null) {
            true
        } else {
            task.ms in thresholdConfiguration.minExecutionTime..thresholdConfiguration.maxExecutionTime
        }
}
