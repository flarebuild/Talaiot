package com.cdsap.talaiot.plugin.rethinkdb

import com.cdsap.talaiot.base.publisher.PublishersConfiguration
import com.cdsap.talaiot.publisher.rethinkdb.RethinkDbPublisherConfiguration
import groovy.lang.Closure
import org.gradle.api.Project

class RethinkDbConfiguration(project: Project) : PublishersConfiguration(project) {

    var rethinkDbPublisher: RethinkDbPublisherConfiguration? = null

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param configuration Configuration block for the [OutputPublisherConfiguration]
     */
    fun rethinkDbPublisher(configuration: RethinkDbPublisherConfiguration.() -> Unit) {
        rethinkDbPublisher = RethinkDbPublisherConfiguration().also(configuration)
    }

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param closure closure for the [OutputPublisherConfiguration]
     */
    fun rethinkDbPublisher(closure: Closure<*>) {
        rethinkDbPublisher = RethinkDbPublisherConfiguration()
        closure.delegate = rethinkDbPublisher
        closure.call()
    }
}