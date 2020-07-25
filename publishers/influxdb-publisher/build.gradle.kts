plugins {
    id("publisherPlugin")
}
dependencies {
    implementation("org.influxdb:influxdb-java:2.19")
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
            artifactId = "influxdb-publisher"
            version =  com.talaiot.buildplugins.Versions.TALAIOT_VERSION
            from(components["kotlin"])
        }
    }
}