plugins {
    id("publisherPlugin")
}

publisher{
    name  = "elasticesearch-publisher"
}

dependencies {
    implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.3.0")
    testImplementation("org.testcontainers:elasticsearch:1.12.0")
    testImplementation("com.google.code.gson:gson:2.8.5")
}
