plugins {
    `maven-publish`
    `java-library`
    `java-gradle-plugin`
    kotlin("jvm")
}


repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.oshi:oshi-core:3.13.3")
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
    testImplementation("org.testcontainers:testcontainers:1.11.3")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")

}
group = "com.cdsap.talaiot"
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}


version = com.talaiot.ver.Versions.version

publishing {
    repositories {
        maven {
            name = "Snapshots"
            url = uri("http://oss.jfrog.org/artifactory/oss-snapshot-local")

            credentials {
                username = System.getenv("USERNAME_SNAPSHOT")
                password = System.getenv("PASSWORD_SNAPSHOT")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cdsap.talaiot"
            artifactId = "base"
            version = com.talaiot.ver.Versions.version
            from(components["kotlin"])
            // from(components["java"])
            //      artifact(sourcesJar.source)

        }
    }
}