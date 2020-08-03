package com.cdsap.talaiot.plugin.elasticsearch
import com.cdsap.talaiot.base.publisher.PublishersConfiguration
import com.cdsap.talaiot.publisher.elasticsearch.ElasticSearchPublisherConfiguration
import groovy.lang.Closure
import org.gradle.api.Project

class  ElasticsearchConfiguration(project: Project) : PublishersConfiguration(project) {

    var elasticSearch: ElasticSearchPublisherConfiguration? = null

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param configuration Configuration block for the [OutputPublisherConfiguration]
     */
    fun elasticSearch(configuration: ElasticSearchPublisherConfiguration.() -> Unit) {
        elasticSearch = ElasticSearchPublisherConfiguration().also(configuration)
    }

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param closure closure for the [OutputPublisherConfiguration]
     */
    fun elasticSearch(closure: Closure<*>) {
        elasticSearch = ElasticSearchPublisherConfiguration()
        closure.delegate = elasticSearch
        closure.call()
    }
}