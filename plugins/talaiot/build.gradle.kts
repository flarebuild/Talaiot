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
    implementation(project(":publishers:base"))
    implementation(project(":publishers:elastic-search"))
    implementation(project(":publishers:graph"))
    implementation(project(":publishers:influxdb"))
    implementation(project(":publishers:push-gateway"))
    implementation(project(":publishers:rethinkdb"))
    implementation(project(":publishers:hybrid"))
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
