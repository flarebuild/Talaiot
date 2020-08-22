package com.cdsap.talaiot.plugin.rethinkdb

import com.cdsap.talaiot.base.Publisher
import com.cdsap.talaiot.base.logger.LogTrackerImpl
import com.cdsap.talaiot.base.provider.PublisherConfigurationProvider
import com.cdsap.talaiot.publisher.rethinkdb.RethinkDbPublisher
import org.gradle.api.Project
import java.util.concurrent.Executors


class RethinkDbConfigurationProvider(
    val project: Project
) : PublisherConfigurationProvider {
    override fun get(): List<Publisher> {
        val publishers = mutableListOf<Publisher>()
        val talaiotExtension = project.extensions.getByName("talaiot") as RethinkDbExtension

        talaiotExtension.publishers?.apply {
            val logger = LogTrackerImpl(talaiotExtension.logger)
            publishers.add(
                RethinkDbPublisher(
                    this.rethinkDbPublisher!!,
                    logger,
                    Executors.newSingleThreadExecutor()
                )
            )
            publishers.addAll(customPublishers)
        }
        return publishers

    }
}