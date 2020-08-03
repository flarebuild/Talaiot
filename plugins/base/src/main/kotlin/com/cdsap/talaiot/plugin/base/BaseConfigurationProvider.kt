package com.cdsap.talaiot.plugin.base

import com.cdsap.talaiot.base.Publisher
import com.cdsap.talaiot.base.logger.LogTrackerImpl
import com.cdsap.talaiot.base.provider.PublisherConfigurationProvider
import com.cdsap.talaiot.publisher.base.JsonPublisher
import com.cdsap.talaiot.publisher.base.OutputPublisher
import com.cdsap.talaiot.publisher.base.TimelinePublisher
import org.gradle.api.Project

class BaseConfigurationProvider(
    val project: Project
) : PublisherConfigurationProvider {
    override fun get(): List<Publisher> {
        val publishers = mutableListOf<Publisher>()
        val talaiotExtension = project.extensions.getByName("talaiot") as BaseExtension
        talaiotExtension.publishers?.apply {
            outputPublisher?.apply {
                publishers.add(OutputPublisher(this, LogTrackerImpl(talaiotExtension.logger)))
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