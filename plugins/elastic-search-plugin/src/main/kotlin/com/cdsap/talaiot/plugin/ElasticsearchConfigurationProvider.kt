package com.cdsap.talaiot.plugin
import com.cdsap.talaiot.base.Publisher
import com.cdsap.talaiot.base.logger.LogTrackerImpl
import com.cdsap.talaiot.base.provider.PublisherConfigurationProvider
import com.cdsap.talaiot.publisher.elasticsearch.ElasticSearchPublisher
import org.gradle.api.Project
import java.util.concurrent.Executors

class ElasticsearchConfigurationProvider(
    val project: Project
) : PublisherConfigurationProvider {
    override fun get(): List<Publisher> {
        val publishers = mutableListOf<Publisher>()
        val talaiotExtension = project.extensions.getByName("talaiot") as ElasticsearchExtension

        talaiotExtension.publishers?.apply {
            publishers.add(
                ElasticSearchPublisher(
                    this.elasticSearch!!,
                    LogTrackerImpl(talaiotExtension.logger),
                    Executors.newSingleThreadExecutor()
                )
            )
            publishers.addAll(customPublishers)
        }
        return publishers

    }
}