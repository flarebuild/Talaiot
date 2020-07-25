plugins {
    id("talaiotPlugin")
}
dependencies {
    implementation(project(":publishers:push-gateway-publisher"))
}

gradlePlugin {
    plugins {
        register("TalaiotPushGateway") {
            id = "com.cdsap.talaiot.plugins.pushgateway"
            implementationClass = "com.cdsap.talaiot.plugin.TalaiotPushGatewayPlugin"
        }
    }
}

pluginBundle {
    (plugins) {
        ("TalaiotPushGateway") {
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