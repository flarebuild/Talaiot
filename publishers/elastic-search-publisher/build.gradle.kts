plugins {
    java
    kotlin("jvm") version "1.3.72"
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":talaiot:base"))
    implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.3.0")
    testImplementation("org.testcontainers:elasticsearch:1.12.0")

    implementation(kotlin("stdlib-jdk8"))
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