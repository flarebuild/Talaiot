plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.3.72"
}


repositories {
    mavenCentral()
}

dependencies {
    api(project(":talaiot:base"))
    implementation("org.influxdb:influxdb-java:2.19")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.influxdb:influxdb-java:2.19")
    testCompile("junit", "junit", "4.12")
    testImplementation("org.testcontainers:influxdb:1.11.3")

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