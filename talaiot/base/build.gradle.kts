import com.talaiot.buildplugins.collectUnitTest

plugins {
    `maven-publish`
    `java-library`
    `java-gradle-plugin`
    kotlin("jvm")
}

collectUnitTest()

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.oshi:oshi-core:3.13.3")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("junit", "junit", "4.12")
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
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
tasks.withType<JacocoReport> {
    reports {
        xml.isEnabled = true
        csv.isEnabled = true
        html.isEnabled = true
        html.destination = File("${rootProject.buildDir}/coverage")
    }
}

apply {
    val test by tasks.getting(Test::class) {
        useJUnitPlatform { }
    }
}

version = com.talaiot.buildplugins.Versions.TALAIOT_VERSION

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
            from(components["kotlin"])
        }
    }
}