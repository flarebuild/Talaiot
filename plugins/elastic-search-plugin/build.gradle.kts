plugins {
     id("talaiotPlugin")
}
dependencies {
     implementation(project(":publishers:elastic-search-publisher"))
}


gradlePlugin {
     plugins {
          register("TalaiotElasticSearch") {
               id = "com.cdsap.talaiot.plugins"
               implementationClass = "com.cdsap.talaiot.plugin.TalaiotElasticsearchPlugin"
          }
     }

}

pluginBundle {
     (plugins) {
          ("TalaiotElasticSearch") {
               displayName = "ElasticSearchPlugin"
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
               artifactId = "elastic-search-plugin"
               version = "0.0.8"
               from(components["kotlin"])
          }
     }
}