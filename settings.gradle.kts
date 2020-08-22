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
include(":library:docs")

include(":library:talaiot:base")
include(":library:talaiot:request")
include(":library:talaiot:test-utils")

include(":library:publishers:push-gateway")
include(":library:publishers:rethinkdb")
include(":library:publishers:elastic-search")
include(":library:publishers:graph")
include(":library:publishers:influxdb")
include(":library:publishers:base")
include(":library:publishers:hybrid")

include(":library:plugins:influxdb")
include(":library:plugins:base")
include(":library:plugins:elastic-search")
include(":library:plugins:graph")
include(":library:plugins:rethinkdb")
include(":library:plugins:push-gateway")
include(":library:plugins:talaiot")

