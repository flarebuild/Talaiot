plugins {
    id("publisherPlugin")
}

publisher{
    name  = "hybrid-publisher"
}

dependencies {
    implementation(project(":library:publishers:elastic-search"))
    implementation(project(":library:publishers:influxdb"))
    implementation(project(":library:publishers:base"))
    implementation(project(":library:publishers:push-gateway"))
    implementation(project(":library:publishers:rethinkdb"))
    testImplementation( "com.rethinkdb:rethinkdb-driver:2.3.3")
    testImplementation("org.influxdb:influxdb-java:2.19")
    testImplementation("org.testcontainers:influxdb:1.11.3")
}