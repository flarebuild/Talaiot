plugins {
    id("talaiotPlugin")
}
dependencies {
    implementation(project(":publishers:rethinkdb-publisher"))
}

gradlePlugin {
    plugins {
        register("TalaiotRethinkDb") {
            id = "com.cdsap.talaiot.plugins.rethinkdb"
            implementationClass = "com.cdsap.talaiot.plugin.TalaiotRethinkDbPlugin"
        }
    }

}

pluginBundle {
    (plugins) {
        ("TalaiotRethinkDb") {
            displayName = "RethinkDbPlugin"
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
            artifactId = "rethinkdb-plugin"
            version = "0.0.8"
            from(components["kotlin"])
        }
    }
}
