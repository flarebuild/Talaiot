package com.cdsap.talaiot.plugin.graph

import com.cdsap.talaiot.base.Publisher
import com.cdsap.talaiot.base.logger.LogTrackerImpl
import com.cdsap.talaiot.base.provider.PublisherConfigurationProvider
import com.cdsap.talaiot.publisher.graph.GraphPublisherFactoryImpl
import com.cdsap.talaiot.publisher.graph.TaskDependencyGraphPublisher
import org.gradle.api.Project
import java.util.concurrent.Executors


class GraphConfigurationProvider(
    val project: Project
) : PublisherConfigurationProvider {
    override fun get(): List<Publisher> {
        val publishers = mutableListOf<Publisher>()
        val talaiotExtension = project.extensions.getByName("talaiot") as GraphExtension

        talaiotExtension.publishers?.apply {
            publishers.add(
                TaskDependencyGraphPublisher(
                    this.taskDependencyGraphPublisher!!,
                    LogTrackerImpl(talaiotExtension.logger),
                    Executors.newSingleThreadExecutor(),
                    GraphPublisherFactoryImpl()
                )
            )
            publishers.addAll(customPublishers)
        }
        return publishers

    }
}