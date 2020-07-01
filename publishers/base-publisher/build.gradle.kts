plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    api(project(":talaiot:base"))
    implementation("com.google.code.gson:gson:2.8.5")
    testCompile("junit", "junit", "4.12")
}

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

val instrumentedJars by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = false
}

group = "com.cdsap.talaiot.publisher"

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
            groupId = "com.cdsap.talaiot.publisher"
            artifactId = "base-publisher"
            version = com.talaiot.ver.Versions.version
            from(components["kotlin"])
            // from(components["java"])
            //      artifact(sourcesJar.source)

        }
    }
}