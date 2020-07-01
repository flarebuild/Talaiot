plugins {
    java
    kotlin("jvm")
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":talaiot:base"))
  //  implementation(project("talaiot:talaiot-base"))
    implementation("io.github.rybalkinsd:kohttp:0.10.0")
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