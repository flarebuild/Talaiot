import org.gradle.internal.impldep.org.eclipse.jgit.lib.RepositoryCache.FileKey.lenient

plugins {
    `kotlin-dsl`
    id("talaiotPlugin")
}

talaiotPlugin {
    idPlugin = "com.cdsap.talaiot"
    artifact = "talaiot"
    group = "com.cdsap"
    mainClass = "com.cdsap.talaiot.legacy.TalaiotPlugin"
    version = "1.3.71-SNAPSHOT"
}

dependencies {
    implementation(project(":library:talaiot"))
    implementation(project(":library:talaiot-logger"))
    implementation(project(":library:talaiot-request"))
    implementation("guru.nidi:graphviz-java:0.8.3")
    implementation("org.influxdb:influxdb-java:2.19")
    implementation("com.github.oshi:oshi-core:3.13.3")
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.3.0")
    implementation("com.rethinkdb:rethinkdb-driver:2.3.3")
    testImplementation(gradleTestKit())
    testImplementation("io.github.rybalkinsd:kohttp:0.10.0")
    testImplementation(project(":library:talaiot-test-utils"))
}


tasks {
    jar.configure {
        configurations.runtimeClasspath.get().incoming.artifactView {
            attributes.attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
        }.files.filter { it.isDirectory }
                .forEach {
                    println(it)
                }


        from(configurations.runtimeClasspath.get().incoming.artifactView {
            attributes.attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
        }.files.filter { it.isDirectory })
    }

}