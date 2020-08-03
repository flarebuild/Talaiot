plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.plugin.elasticsearch"
    artifact = "elasticsearch"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.elasticsearch.TalaiotElasticsearchPlugin"
}

dependencies {
    implementation(project(":publishers:elastic-search"))
}
