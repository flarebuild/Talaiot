package com.cdsap.talaiot.base.publisher

import com.cdsap.talaiot.base.Publisher

import org.gradle.api.Project


/**
 * Main configuration for the publishers.
 *
 * It offers the accessors for Groovy and KTS
 *
 * ```
 * publishers {
 *    influxDbPublisher {
 *    }
 *    outputPublisher {
 *    }
 *    taskDependencyGraphPublisher {
 *    }
 *    pushGatewayPublisher {
 *    }
 *    customDependencies {
 *    }
 * }
 * ```
 */
open class PublishersConfiguration(
    val project: Project
) {
    var customPublishers: MutableSet<Publisher> = mutableSetOf()

    /**
     * Adds the given custom publishers into the publisher list.
     *
     * @param publishers takes N [Publisher]s to be added to the publishers list.
     */
    fun customPublishers(vararg publishers: Publisher) {
        customPublishers.addAll(publishers)
    }
}
