package com.cdsap.talaiot.publisher.rethinkdb

import io.kotlintest.specs.BehaviorSpec

class RethinkDbPublisherConfigurationTest : BehaviorSpec({
    given("Any of the Publisher configuration") {

        `when`("There is no additional setup") {
            val rethinkDbPublisherConfiguration = RethinkDbPublisherConfiguration()
            then("Publish tasks and build by default") {
                assert(rethinkDbPublisherConfiguration.publishTaskMetrics)
                assert(rethinkDbPublisherConfiguration.publishBuildMetrics)
            }
        }

    }
})