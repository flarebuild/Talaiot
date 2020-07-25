plugins {
    id("publisherPlugin")
}

dependencies {
    implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.3.0")
    testImplementation("org.testcontainers:elasticsearch:1.12.0")
    testImplementation("com.google.code.gson:gson:2.8.5")
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
            artifactId = "elasticesearch-publisher"
            version =  com.talaiot.buildplugins.Versions.TALAIOT_VERSION
            from(components["kotlin"])
        }
    }
}