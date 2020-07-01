package com.cdsap.talaiot.publisher.base

import com.cdsap.base.publisher.entities.TaskMessageState

data class TimelineTaskMeasurement(
    val taskPath: String,
    val stateType: TaskMessageState,
    val critical: Boolean,
    val start: Long,
    val worker: String,
    val end: Long
)
