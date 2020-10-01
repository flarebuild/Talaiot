pluginManagement {
    repositories {
        mavenCentral()
        jcenter()
        maven(url = "https://dl.bintray.com/kotlin/ktor")
        gradlePluginPortal()
        maven(url = "https://jitpack.io")

    }
}
include(":library:docs")
include(":library:core:talaiot")
include(":library:core:talaiot-request")
include(":library:core:talaiot-logger")
include(":library:core:talaiot-test-utils")
include(":library:plugins:talaiot")
include(":library:plugins:base:plugin")
include(":library:plugins:base:publisher")
