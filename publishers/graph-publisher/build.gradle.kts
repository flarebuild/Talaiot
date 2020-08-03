plugins {
    id("publisherPlugin")
}

publisher{
    name  = "graph-publisher"
}

dependencies {
    implementation("guru.nidi:graphviz-java:0.8.3")
}
