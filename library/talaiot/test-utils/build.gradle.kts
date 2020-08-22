plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

//collectUnitTest()

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":library:talaiot:base"))
    implementation("org.testcontainers:influxdb:1.11.3")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}