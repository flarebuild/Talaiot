package com.talaiot.buildplugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File
import java.net.URI


class PublisherPlugin : Plugin<Project> {

    override fun apply(target: Project) {

        target
            .extensions
            .create<PublisherPluginExtension>("publisher")

        target.plugins.apply("maven-publish")
        target.plugins.apply("kotlin")
        target.plugins.apply("jacoco")

        target.repositories {
            jcenter()
            mavenCentral()
        }

        target.dependencies {
            add("implementation", project(":library:talaiot:base"))
            add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
            add("testImplementation", "junit:junit:4.12")
            add("testImplementation", "io.kotlintest:kotlintest-runner-junit5:3.3.2")
            add("testImplementation", "com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
            add("testImplementation", project(":library:talaiot:test-utils"))
        }

        target.tasks.withType<JacocoReport> {
            reports {
                xml.isEnabled = true
                csv.isEnabled = true
                html.isEnabled = true
                html.destination = File("${target.rootProject.buildDir}/coverage")
            }
        }

        target.apply {
            val test by target.tasks.getting(Test::class) {
                useJUnitPlatform { }
            }
        }

        val instrumentedJars by target.configurations.creating {
            isCanBeConsumed = true
            isCanBeResolved = false
        }

        target.group = "com.cdsap.talaiot.publisher"
        target.version =  Versions.TALAIOT_VERSION

        target.afterEvaluate {
            collectUnitTest()
            target.configure<PublishingExtension> {
                repositories {
                    maven {
                        name = "Snapshots"
                        url = URI("http://oss.jfrog.org/artifactory/oss-snapshot-local")

                        credentials {
                            username = System.getenv("USERNAME_SNAPSHOT")
                            password = System.getenv("PASSWORD_SNAPSHOT")
                        }
                    }
                }
                publications {
                    create<MavenPublication>("maven") {
                        val extension = target.extensions.getByType<PublisherPluginExtension>()
                        groupId = target.group.toString()
                        version =  Versions.TALAIOT_VERSION
                        artifactId = extension.name
                        from(components["kotlin"])
                    }
                }
            }

        }

    }
}
