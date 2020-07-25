package com.cdsap.talaiot.publisher.elasticsearch

import io.kotlintest.specs.BehaviorSpec

class ElasticSearchPublisherConfigurationTest : BehaviorSpec({
    given("Any of the Publisher configuration") {

        `when`("There is no additional setup") {
            val elasticSearchPublisherConfiguration = ElasticSearchPublisherConfiguration()
            then("Publish tasks and build by default") {
                assert(elasticSearchPublisherConfiguration.publishTaskMetrics)
                assert(elasticSearchPublisherConfiguration.publishBuildMetrics)
            }
        }

    }
})