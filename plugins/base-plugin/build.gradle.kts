plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.plugin.base"
    artifact = "base"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.TalaiotBasePlugin"
}

dependencies {
    implementation(project(":publishers:base-publisher"))
}
