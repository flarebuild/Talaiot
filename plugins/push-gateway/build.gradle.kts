plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.plugin.pushgateway"
    artifact = "pushgateway"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.pushgateway.TalaiotPushGatewayPlugin"
}

dependencies {
    implementation(project(":publishers:push-gateway"))
}
