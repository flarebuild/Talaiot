plugins {
    id("talaiotPlugin")
}
dependencies {
    implementation(project(":publishers:influxdb-publisher"))
}

gradlePlugin {
    plugins {
        register("TalaiotInfluxdb") {
            id = "com.cdsap.talaiot.plugins.influxdb"
            implementationClass = "com.cdsap.talaiot.plugin.TalaiotInfluxdbPlugin"
        }
    }

}

pluginBundle {
    (plugins) {
        ("TalaiotInfluxdb") {
            displayName = "InfluxdbPlugin"
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
            artifactId = "influxdb-plugin"
            version = "0.0.8"
            from(components["kotlin"])
        }
    }
}