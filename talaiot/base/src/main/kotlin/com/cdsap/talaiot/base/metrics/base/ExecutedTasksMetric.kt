package com.cdsap.talaiot.base.metrics.base

import com.cdsap.talaiot.base.entities.ExecutedTasksInfo
import com.cdsap.talaiot.base.entities.ExecutionReport


/**
 * [Metric] that operates on [ExecutedTasksInfo]
 */
abstract class ExecutedTasksMetric<T>(provider: (ExecutedTasksInfo) -> T, assigner: (ExecutionReport, T) -> Unit): Metric<T, ExecutedTasksInfo>(provider, assigner)
