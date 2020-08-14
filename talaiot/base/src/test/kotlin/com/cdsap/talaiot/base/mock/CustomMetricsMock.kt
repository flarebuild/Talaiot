package com.cdsap.talaiot.base.mock

import com.cdsap.talaiot.base.metrics.SimpleMetric
import com.cdsap.talaiot.base.metrics.base.CmdMetric

class AdbVersionMetric : CmdMetric(
    cmd = "adb version",
    assigner = { report, value ->
        report.customProperties.buildProperties["adbVersion"] = value
    }
)

class KotlinVersionMetric : SimpleMetric<String>(
    provider = { "1.4" },
    assigner = { report, value ->
        report.customProperties.buildProperties["kotlinVersion"] = value
    }
)
