package com.cdsap.talaiot.plugin

import com.cdsap.talaiot.base.publisher.PublishersConfiguration
import com.cdsap.talaiot.publisher.pushgateway.PushGatewayPublisherConfiguration
import groovy.lang.Closure
import org.gradle.api.Project

class PushGatewayConfiguration(project: Project) : PublishersConfiguration(project) {

    var pushGatewayPublisher: PushGatewayPublisherConfiguration? = null

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param configuration Configuration block for the [OutputPublisherConfiguration]
     */
    fun pushGatewayPublisher(configuration: PushGatewayPublisherConfiguration.() -> Unit) {
        pushGatewayPublisher = PushGatewayPublisherConfiguration().also(configuration)
    }

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param closure closure for the [OutputPublisherConfiguration]
     */
    fun pushGatewayPublisher(closure: Closure<*>) {
        pushGatewayPublisher = PushGatewayPublisherConfiguration()
        closure.delegate = pushGatewayPublisher
        closure.call()
    }
}