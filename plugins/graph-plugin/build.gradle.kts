plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.graph-plugin"
    artifact = "graph"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.TalaiotGraphPlugin"
}

dependencies {
    implementation(project(":publishers:graph-publisher"))
}
