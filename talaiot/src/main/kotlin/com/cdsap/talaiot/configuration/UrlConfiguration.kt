package com.cdsap.talaiot.configuration

class UrlConfiguration : PublisherConfiguration {
    override var publishBuildMetrics: Boolean = true
    override var publishTaskMetrics: Boolean = true

    /**
     * name of the publisher
     */
    override var name: String = "url"

    serializer

}