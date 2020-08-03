plugins {
    id("publisherPlugin")
}

publisher{
    name  = "influxdb-publisher"
}

dependencies {
    implementation("org.influxdb:influxdb-java:2.19")
    testImplementation("org.testcontainers:influxdb:1.11.3")
}