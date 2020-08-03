plugins {
    id("publisherPlugin")
}

publisher{
    name  = "push-gateway-publisher"
}

dependencies {
    api(project(":talaiot:request"))
    testImplementation("org.testcontainers:testcontainers:1.11.3")
    testImplementation("io.github.rybalkinsd:kohttp:0.10.0")
}
