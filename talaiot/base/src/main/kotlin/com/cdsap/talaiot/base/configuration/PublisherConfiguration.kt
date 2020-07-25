package com.cdsap.talaiot.base.configuration

interface PublisherConfiguration {
    var name: String
    var publishBuildMetrics: Boolean
    var publishTaskMetrics: Boolean
}