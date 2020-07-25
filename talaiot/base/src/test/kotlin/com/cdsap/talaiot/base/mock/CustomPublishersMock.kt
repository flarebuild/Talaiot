package com.cdsap.talaiot.base.mock

import com.cdsap.talaiot.base.Publisher
import com.cdsap.talaiot.base.entities.ExecutionReport

class TestPublisher : Publisher {
    override fun publish(report: ExecutionReport) {}
}

class ConsolePublisher : Publisher {
    override fun publish(report: ExecutionReport) {}
}
