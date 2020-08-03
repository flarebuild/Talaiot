plugins {
    id("talaiotPlugin")
}
talaiotPlugin{
    idPlugin = "com.cdsap.talaiot"
    artifact = "talaiot"
    group = "com.cdsap"
    mainClass = "com.cdsap.talaiot.plugin.TalaiotPlugin"
}

dependencies {
    implementation(project(":publishers:base-publisher"))
    implementation(project(":publishers:elastic-search-publisher"))
    implementation(project(":publishers:graph-publisher"))
    implementation(project(":publishers:influxdb-publisher"))
    implementation(project(":publishers:push-gateway-publisher"))
    implementation(project(":publishers:rethinkdb-publisher"))
    implementation(project(":publishers:hybrid-publisher"))
    testImplementation("org.influxdb:influxdb-java:2.19")
    testImplementation( "io.kotlintest:kotlintest-runner-junit5:3.3.2")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
    testImplementation(gradleTestKit())
    testImplementation("org.testcontainers:testcontainers:1.11.3")
    testImplementation("org.testcontainers:influxdb:1.11.3")
    testImplementation("org.testcontainers:elasticsearch:1.12.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
    testImplementation("org.testcontainers:testcontainers:1.11.3")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
    testImplementation("com.google.code.gson:gson:2.8.6")
}
