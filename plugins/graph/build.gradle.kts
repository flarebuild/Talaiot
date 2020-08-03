plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.plugin.graph"
    artifact = "graph"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.graph.TalaiotGraphPlugin"
}

dependencies {
    implementation(project(":publishers:graph"))
}
