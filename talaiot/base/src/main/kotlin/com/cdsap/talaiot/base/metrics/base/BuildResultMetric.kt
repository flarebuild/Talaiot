package com.cdsap.talaiot.base.metrics.base

import com.cdsap.talaiot.base.entities.ExecutionReport
import org.gradle.BuildResult

/**
 * [Metric] that operates on [BuildResult]
 */
open class BuildResultMetric<T>(
    provider: (BuildResult) -> T,
    assigner: (ExecutionReport, T) -> Unit
) : Metric<T, BuildResult>(provider, assigner)