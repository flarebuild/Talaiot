package com.cdsap.talaiot.publisher.pushgateway

import io.kotlintest.specs.BehaviorSpec

class PublisherConfigurationTest : BehaviorSpec({
    given("Any of the Publisher configuration") {

        `when`("There is no additional setup") {
            val pushGatewayPublisherConfiguration = PushGatewayPublisherConfiguration()
            then("Publish tasks and build by default") {
                assert(pushGatewayPublisherConfiguration.publishBuildMetrics)
                assert(pushGatewayPublisherConfiguration.publishTaskMetrics)
            }
        }

    }
})