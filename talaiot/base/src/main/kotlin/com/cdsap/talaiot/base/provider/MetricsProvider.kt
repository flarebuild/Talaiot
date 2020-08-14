package com.cdsap.talaiot.base.provider

import com.cdsap.talaiot.base.extension.TalaiotExtension
import com.cdsap.talaiot.base.entities.ExecutedTasksInfo
import com.cdsap.talaiot.base.entities.ExecutionReport
import com.cdsap.talaiot.base.metrics.SimpleMetric
import com.cdsap.talaiot.base.metrics.base.BuildResultMetric
import com.cdsap.talaiot.base.metrics.base.ExecutedTasksMetric
import com.cdsap.talaiot.base.metrics.base.GradleMetric
import org.gradle.BuildResult
import org.gradle.api.Project

/**
 * Provider for all metrics defined in the main [com.cdsap.talaiot.configuration.MetricsConfiguration].
 */
class MetricsProvider(
    /**
     * Gradle project required to access [TalaiotExtension]
     */
    private val project: Project,
    private val buildResult: BuildResult,
    /**
     * Information about all tasks that were executed
     */
    private val executedTasksInfo: ExecutedTasksInfo
) : Provider<ExecutionReport> {

    /**
     * Aggregates all metrics based on [com.cdsap.talaiot.configuration.MetricsConfiguration].
     *
     * @return execution report
     */
    override fun get(): ExecutionReport {
        val report = ExecutionReport()

        val talaiotExtension = project.extensions.getByName("talaiot") as TalaiotExtension
        val metrics = talaiotExtension.metrics.build()

        /**
         * Could be optimized but for < 100 metrics performance shouldn't be an issue
         */
        metrics.forEach { metric ->
            when (metric) {
                is GradleMetric -> metric.get(project, report)
                is SimpleMetric -> metric.get(Unit, report)
                is BuildResultMetric -> metric.get(buildResult, report)
                is ExecutedTasksMetric -> metric.get(executedTasksInfo, report)
            }
        }

        return report
    }
}
