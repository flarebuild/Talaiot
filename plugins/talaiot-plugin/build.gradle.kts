plugins {
    id("talaiotPlugin")
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

gradlePlugin {
    plugins {
        register("TalaiotPlugin") {
            id = "com.cdsap.talaiot"
            implementationClass = "com.cdsap.talaiot.plugin.TalaiotPlugin"
        }
    }
}

pluginBundle {
    (plugins) {
        ("TalaiotPlugin") {
            displayName = "PushGatewayPlugin"
            description =
                "Simple and extensible plugin to track task and build times in your Gradle Project."
            tags = listOf("tracking", "kotlin", "gradle")
            version =  com.talaiot.buildplugins.Versions.TALAIOT_VERSION
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cdsap.talaiot.plugin"
            artifactId = "push-gateway-plugin"
            version = "0.0.8"
            from(components["kotlin"])
        }
    }
}