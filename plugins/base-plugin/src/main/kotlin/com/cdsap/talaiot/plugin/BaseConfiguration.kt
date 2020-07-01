package com.cdsap.talaiot.plugin

import com.cdsap.base.publisher.PublishersConfiguration
import com.cdsap.base.publisher.publisher.Publisher
import com.cdsap.talaiot.publisher.base.OutputPublisherConfiguration
import groovy.lang.Closure
import org.gradle.api.Project

class BaseConfiguration(project: Project) : PublishersConfiguration(project) {
    internal var outputPublisher: OutputPublisherConfiguration? = null

    /**
     * Enables a [TimelinePublisher] if set to `true`. Disabled by default.
     */
    var timelinePublisher: Boolean = false

    /**
     * Enables a [JsonPublisher] if set to `true`. Disabled by default.
     */
    var jsonPublisher: Boolean = false

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param configuration Configuration block for the [OutputPublisherConfiguration]
     */
    fun outputPublisher(configuration: OutputPublisherConfiguration.() -> Unit) {
        outputPublisher = OutputPublisherConfiguration().also(configuration)
    }

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param closure closure for the [OutputPublisherConfiguration]
     */
    fun outputPublisher(closure: Closure<*>) {
        outputPublisher = OutputPublisherConfiguration()
        closure.delegate = outputPublisher
        closure.call()
    }

    /**
     * Adds the given custom publishers into the publisher list.
     *
     * @param publishers takes N [Publisher]s to be added to the publishers list.
     */
    fun customPublishers(vararg publishers: Publisher) {
        customPublishers.addAll(publishers)
    }


}