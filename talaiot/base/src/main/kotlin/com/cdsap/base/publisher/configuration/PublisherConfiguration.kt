package com.cdsap.base.publisher.configuration

interface PublisherConfiguration {
    var name: String
    var publishBuildMetrics: Boolean
    var publishTaskMetrics: Boolean
}