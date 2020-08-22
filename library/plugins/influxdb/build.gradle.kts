plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.plugin.influxdb"
    artifact = "influxdb"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.influxdb.TalaiotInfluxdbPlugin"
}

dependencies {
    implementation(project(":library:publishers:influxdb"))
}
