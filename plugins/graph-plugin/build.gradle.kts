plugins {
    id("talaiotPlugin")
}
dependencies {
    implementation(project(":publishers:graph-publisher"))
}

gradlePlugin {
    plugins {
        register("TalaiotGraph") {
            id = "com.cdsap.talaiot.plugins.graph"
            implementationClass = "com.cdsap.talaiot.plugin.TalaiotGraphPlugin"
        }
    }

}

pluginBundle {
    (plugins) {
        ("TalaiotGraph") {
            displayName = "GraphPlugin"
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
            artifactId = "graph-plugin"
            version = "0.0.8"
            from(components["kotlin"])
        }
    }
}