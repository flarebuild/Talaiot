plugins {
    id("publisherPlugin")
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
val instrumentedJars by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}
group = "com.cdsap.talaiot.publisher"
version =  com.talaiot.buildplugins.Versions.TALAIOT_VERSION

publishing {
    repositories {
        maven {
            name = "Snapshots"
            url = uri("http://oss.jfrog.org/artifactory/oss-snapshot-local")

            credentials {
                username = System.getenv("USERNAME_SNAPSHOT")
                password = System.getenv("PASSWORD_SNAPSHOT")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cdsap.talaiot.publisher"
            artifactId = "hybrid-publisher"
            version =  com.talaiot.buildplugins.Versions.TALAIOT_VERSION
            from(components["kotlin"])
        }
    }
}