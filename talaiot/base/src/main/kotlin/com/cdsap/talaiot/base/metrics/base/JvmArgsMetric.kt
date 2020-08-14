package com.cdsap.talaiot.base.metrics.base

import com.cdsap.talaiot.base.entities.ExecutionReport

/**
 * [Metric] that operates on jvm arguments passed to the build
 */
abstract class JvmArgsMetric(val argProvider: (List<String>) -> String?,
                             assigner: (ExecutionReport, String?) -> Unit): GradleMetric<String?>(
    provider = {
        if (it.gradle.rootProject.hasProperty("org.gradle.jvmargs")) {
            val properties: String = it.gradle.rootProject.property("org.gradle.jvmargs") as String
            argProvider(properties.split(" "))
        } else {
            null
        }
    },
    assigner = assigner
)