package com.cdsap.talaiot.base.metrics

import com.cdsap.talaiot.base.metrics.base.CmdMetric

class GitBranchMetric: CmdMetric(
    cmd = "git rev-parse --abbrev-ref HEAD",
    assigner = { report, value -> report.environment.gitBranch = value }
)
class GitUserMetric: CmdMetric(
    cmd = "git config --get user.name",
    assigner = { report, value -> report.environment.gitUser = value }
)