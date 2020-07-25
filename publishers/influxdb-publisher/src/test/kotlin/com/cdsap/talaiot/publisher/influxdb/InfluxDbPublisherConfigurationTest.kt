package com.cdsap.talaiot.publisher.influxdb

import io.kotlintest.specs.BehaviorSpec

class InfluxDbPublisherConfigurationTest : BehaviorSpec({
    given("Any of the Publisher configuration") {

        `when`("There is no additional setup") {
            val influxDbPublisherConfiguration = InfluxDbPublisherConfiguration()
            then("Publish tasks and build by default") {
                assert(influxDbPublisherConfiguration.publishBuildMetrics)
                assert(influxDbPublisherConfiguration.publishTaskMetrics)
            }
        }

    }
})