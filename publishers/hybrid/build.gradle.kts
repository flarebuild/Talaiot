plugins {
    id("publisherPlugin")
}

publisher{
    name  = "hybrid-publisher"
}

dependencies {
    implementation(project(":publishers:elastic-search"))
    implementation(project(":publishers:influxdb"))
    implementation(project(":publishers:base"))
    implementation(project(":publishers:push-gateway"))
    implementation(project(":publishers:rethinkdb"))
    testImplementation( "com.rethinkdb:rethinkdb-driver:2.3.3")
    testImplementation("org.influxdb:influxdb-java:2.19")
    testImplementation("org.testcontainers:influxdb:1.11.3")
}