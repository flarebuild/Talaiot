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

class CorePlugin : Plugin<Project> {
    override fun apply(target: Project) {

        target
            .extensions
            .create<CorePluginExtension>("core")

        target.plugins.apply("maven-publish")
        target.plugins.apply("jacoco")
        target.plugins.apply("kotlin")

        target.repositories {
            jcenter()
            mavenCentral()
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

        target.afterEvaluate {
            collectUnitTest()
            configPublishing(target)
        }
    }

    private fun configPublishing(target: Project) {
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
            target.version =  Versions.TALAIOT_VERSION
            target.group = "com.cdsap.talaiot"
            publications {
                create<MavenPublication>("maven") {
                    val extension = target.extensions.getByType<CorePluginExtension>()

                    groupId = target.group.toString()
                    artifactId = extension.name
                    version = Versions.TALAIOT_VERSION
                    from(target.components["kotlin"])
                }
            }
        }
    }
}