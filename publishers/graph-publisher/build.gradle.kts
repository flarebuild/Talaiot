plugins {
    `java-gradle-plugin`
    kotlin("jvm") version "1.3.72"
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    api(project(":talaiot:base"))
    implementation("guru.nidi:graphviz-java:0.8.3")
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