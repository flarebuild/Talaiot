plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.pushgateway-plugin"
    artifact = "pushgateway"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.TalaiotPushGatewayPlugin"
}

dependencies {
    implementation(project(":publishers:push-gateway-publisher"))
}
