plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.influxdb-plugin"
    artifact = "influxdb"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.TalaiotInfluxdbPlugin"
}

dependencies {
    implementation(project(":publishers:influxdb-publisher"))
}
