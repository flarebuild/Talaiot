pluginManagement {
    repositories {
        mavenCentral()
        google()
        jcenter()
        mavenLocal()
        maven(url = "https://dl.bintray.com/kotlin/ktor")
        gradlePluginPortal()
        maven(url = "https://jitpack.io")

    }
}
include(":docs")

include(":talaiot:base")
include(":talaiot:request")
include(":talaiot:test-utils")

include(":publishers:push-gateway")
include(":publishers:rethinkdb")
include(":publishers:elastic-search")
include(":publishers:graph")
include(":publishers:influxdb")
include(":publishers:base")
include(":publishers:hybrid")

include(":plugins:influxdb")
include(":plugins:base")
include(":plugins:elastic-search")
include(":plugins:graph")
include(":plugins:rethinkdb")
include(":plugins:push-gateway")
include(":plugins:talaiot")

