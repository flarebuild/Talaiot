package com.talaiot.buildplugins

import com.gradle.publish.PluginBundleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestReport
import org.gradle.kotlin.dsl.*
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

import java.io.File
import java.net.URI


class TalaiotPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target
            .extensions
            .create<TalaiotPluginExtension>("talaiotPlugin")
        target.version = Versions.TALAIOT_VERSION

        target.plugins.apply("java-gradle-plugin")
        target.plugins.apply("maven-publish")
        target.plugins.apply("jacoco")
        target.plugins.apply("kotlin")
        target.plugins.apply("com.gradle.plugin-publish")

        target.repositories {
            jcenter()
            mavenCentral()
        }

        target.dependencies {
            add("implementation", project(":library:talaiot:base"))
            add("testImplementation", "com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
            add("testImplementation", "io.kotlintest:kotlintest-runner-junit5:3.3.2")
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
        target.version =  Versions.TALAIOT_VERSION
        target.afterEvaluate {
            collectUnitTest()
            configGradlePlugin(target)
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
            target.group = "com.cdsap.talaiot.plugin"
            publications {
                create<MavenPublication>("maven") {
                    val extension = target.extensions.getByType<TalaiotPluginExtension>()
                    groupId = extension.group
                    artifactId = extension.artifact
                    version = Versions.TALAIOT_VERSION
                    from(target.components["kotlin"])
                }
            }
        }
    }

    private fun configGradlePlugin(project: Project) {

        project.configure<GradlePluginDevelopmentExtension> {
            plugins {
                register(project.name) {
                    val extension = project.extensions.getByType<TalaiotPluginExtension>()
                    id = extension.idPlugin
                    implementationClass = extension.mainClass
                }
            }
        }

        project.configure<PluginBundleExtension>() {
            (plugins){
                (project.name){
                    val extension = project.extensions.getByType<TalaiotPluginExtension>()
                    displayName = project.name
                    description =
                        "Simple and extensible plugin to track task and build times in your Gradle Project."
                    tags = listOf("tracking", "kotlin", "gradle")
                    version = com.talaiot.buildplugins.Versions.TALAIOT_VERSION
                }
            }
        }
    }
}
