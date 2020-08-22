plugins {
    id("publisherPlugin")
}

publisher{
    name  =  "rethinkdb-publisher"
}

dependencies {
    implementation("com.rethinkdb:rethinkdb-driver:2.3.3")
    testImplementation("org.testcontainers:testcontainers:1.11.3")
}
