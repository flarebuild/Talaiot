plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("jacoco")
    kotlin("jvm") version "1.3.60"
    id("com.gradle.plugin-publish") version "0.12.0"
}

jacoco {
    toolVersion = "0.8.3"
}

val versionTalaiot = "1.3.9-SNAPSHOT"

group = "com.cdsap"

version = versionTalaiot

gradlePlugin {
    plugins {
        register("Talaiot") {
            id = "com.cdsap.talaiot-elasticsearch"
            implementationClass = "com.cdsap.talaiot.plugin.TalaiotElasticsearchBasePlugin"
        }
        dependencies {
            api(project(":publishers:base-publisher"))
            api(project(":talaiot:base"))
            testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
        }
    }
}

pluginBundle {
    website = "https://github.com/cdsap/Talaiot/"
    vcsUrl = "https://github.com/cdsap/Talaiot/"
    tags = listOf("kotlin", "gradle", "kotlin-dsl")
    (plugins) {
        "Talaiot" {
            displayName = "TalaiotBase"
            description = "Simple and extensible plugin to track task and build times in your Gradle Project."
            tags = listOf("tracking", "kotlin", "gradle")
            version = versionTalaiot
        }
    }
}

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
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform { }
}

repositories {
    jcenter()
    mavenCentral()
    google()
    mavenLocal()
}

tasks.jacocoTestReport {
    reports {
        xml.isEnabled = true
        csv.isEnabled = true
        html.isEnabled = true
        html.destination = file("$buildDir/reports/coverage")
    }
}