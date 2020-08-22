plugins {
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot.plugin.rethinkdb"
    artifact = "rethinkdb"
    group = "com.cdsap.talaiot.plugin"
    mainClass = "com.cdsap.talaiot.plugin.rethinkdb.TalaiotRethinkDbPlugin"
}

dependencies {
    implementation(project(":library:publishers:rethinkdb"))
}
