plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.elasticsearch-plugin"
    artifact = "elasticsearch"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.TalaiotElasticsearchPlugin"
}

dependencies {
    implementation(project(":publishers:elastic-search-publisher"))
}
