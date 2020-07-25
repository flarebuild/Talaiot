package com.cdsap.talaiot.plugin

import com.cdsap.talaiot.base.Publisher
import com.cdsap.talaiot.base.logger.LogTrackerImpl
import com.cdsap.talaiot.base.provider.PublisherConfigurationProvider
import com.cdsap.talaiot.publisher.pushgateway.PushGatewayFormatter
import com.cdsap.talaiot.publisher.pushgateway.PushGatewayPublisher
import com.cdsap.talaiot.request.SimpleRequest
import org.gradle.api.Project
import java.util.concurrent.Executors


class PushGatewayConfigurationProvider(
    val project: Project
) : PublisherConfigurationProvider {
    override fun get(): List<Publisher> {
        val publishers = mutableListOf<Publisher>()
        val talaiotExtension = project.extensions.getByName("talaiot") as PushGatewayExtension

        talaiotExtension.publishers?.apply {
            val logger = LogTrackerImpl(talaiotExtension.logger)
            publishers.add(
                PushGatewayPublisher(
                    this.pushGatewayPublisher!!,
                    logger,
                    SimpleRequest(logger),
                    Executors.newSingleThreadExecutor(),
                    PushGatewayFormatter()
                )
            )
            publishers.addAll(customPublishers)
        }
        return publishers

    }
}