plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.rethinkdb-plugin"
    artifact = "rethinkdb"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.TalaiotRethinkDbPlugin"
}

dependencies {
    implementation(project(":publishers:rethinkdb-publisher"))
}
