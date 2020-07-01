package com.cdsap.talaiot.plugin

import com.cdsap.base.publisher.logger.LogTracker
import com.cdsap.base.publisher.provider.PublisherConfigurationProvider
import com.cdsap.base.publisher.publisher.Publisher
import com.cdsap.talaiot.publisher.base.JsonPublisher
import com.cdsap.talaiot.publisher.base.OutputPublisher
import com.cdsap.talaiot.publisher.base.TimelinePublisher
import org.gradle.api.Project

class BaseConfigurationProvider(
    val project: Project,
    val logger: LogTracker
) : PublisherConfigurationProvider {
    override fun get(): List<Publisher> {
        val publishers = mutableListOf<Publisher>()
        val talaiotExtension = project.extensions.getByName("talaiot") as BaseExtension

        talaiotExtension.publishers?.apply {
            outputPublisher?.apply {
                publishers.add(OutputPublisher(this, logger))
            }
            if (jsonPublisher) {
                publishers.add(JsonPublisher(project.gradle))
            }

            if (timelinePublisher) {
                publishers.add(TimelinePublisher(project.gradle))
            }
            publishers.addAll(customPublishers)
        }
        return publishers

    }
}