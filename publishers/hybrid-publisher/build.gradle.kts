plugins {
    id("publisherPlugin")
}

publisher{
    name  = "hybrid-publisher"
}

dependencies {
    implementation(project(":publishers:elastic-search-publisher"))
    implementation(project(":publishers:influxdb-publisher"))
    implementation(project(":publishers:base-publisher"))
    implementation(project(":publishers:push-gateway-publisher"))
    implementation(project(":publishers:rethinkdb-publisher"))
    testImplementation( "com.rethinkdb:rethinkdb-driver:2.3.3")
    testImplementation("org.influxdb:influxdb-java:2.19")
    testImplementation("org.testcontainers:influxdb:1.11.3")
}