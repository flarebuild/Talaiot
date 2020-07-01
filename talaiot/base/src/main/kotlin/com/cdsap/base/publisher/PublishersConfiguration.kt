package com.cdsap.base.publisher

import com.cdsap.base.publisher.publisher.Publisher
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
}
