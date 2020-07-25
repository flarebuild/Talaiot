plugins {
    id("talaiotPlugin")
}

dependencies {
    implementation(project(":publishers:base-publisher"))
}


gradlePlugin {
    plugins {
        register("TalaiotBase") {
            id = "com.cdsap.talaiot.plugins.base"
            implementationClass = "com.cdsap.talaiot.plugin.TalaiotBasePlugin"
        }
    }

}

pluginBundle {
    (plugins) {
        ("TalaiotBase") {
            displayName = "BasePlugin"
            description =
                "Simple and extensible plugin to track task and build times in your Gradle Project."
            tags = listOf("tracking", "kotlin", "gradle")
            version = com.talaiot.buildplugins.Versions.TALAIOT_VERSION
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cdsap.talaiot.plugin"
            artifactId = "base-plugin"
            version = "0.0.8"
            from(components["kotlin"])
        }
    }
}