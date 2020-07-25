package com.talaiot.buildplugins

import com.gradle.publish.PluginBundleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.*
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.io.File


class PublisherPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("maven-publish")
        target.plugins.apply("kotlin")
        target.plugins.apply("jacoco")

        target.repositories {
            jcenter()
            mavenCentral()
        }

        target.dependencies {
            add("implementation", project(":talaiot:base"))
            add("implementation", "org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
            add("testImplementation", "junit:junit:4.12")
            add("testImplementation", "io.kotlintest:kotlintest-runner-junit5:3.3.2")
            add("testImplementation", "com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
            add("testImplementation", project(":test-utils"))
        }

        target.tasks.withType<JacocoReport> {
            reports {
                xml.isEnabled = true
                csv.isEnabled = true
                html.isEnabled = true
                html.destination = File("reports/coverage")
            }
        }

        target.apply {
            val test by target.tasks.getting(Test::class) {
                useJUnitPlatform { }
            }
        }
    }
}
