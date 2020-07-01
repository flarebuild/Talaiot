package com.talaiot.ver.plugin.entrypoint

import com.gradle.publish.PluginBundleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.gradle.plugin.devel.GradlePluginDevelopmentExtension
import org.jetbrains.kotlin.statistics.anonymizeComponentVersion

class ReporterPlugin : Plugin<Project> {
    val versionTalaiot = com.talaiot.ver.Versions.version

    override fun apply(target: Project) {
        //      target
        //          .extensions
        //          .create<ReporterExtension>("reporter")
        target.plugins.apply("kotlin-dsl")
        target.plugins.apply("maven-publish")
        target.plugins.apply("jacoco")
        target.plugins.apply("kotlin")
        target.plugins.apply("java-gradle-plugin")
        // target.plugins.apply("com.gradle.plugin-publish").also {
        //     anonymizeComponentVersion("1.2.0")
        // }

        target.repositories {
            jcenter()
            mavenCentral()
        }

        val gradleExtension = target.extensions.getByType(GradlePluginDevelopmentExtension::class.java)
        gradleExtension.plugins {
            register("Talaiotx") {
                id = "com.cdsap.talaiot.base"
                //    group = "com.cdsap"
                implementationClass = "com.cdsap.talaiot.plugin.TalaiotBasePlugin"
            }
            target.dependencies {
                add("implementation", project(":publishers:base-publisher"))
                add("implementation", project(":talaiot:base"))
            }
            //target.pl

            //     target.dependencies


//            dependencies {
//                implementation(project(":publishers:base-publisher"))
//                implementation(project(":talaiot:base"))
//                //testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0-RC1")
//            }

        }
        //  group = "com.cdsap"


        val pluginBundle = target.extensions.getByType(PluginBundleExtension::class.java)
        pluginBundle.apply {
            website = "https://github.com/cdsap/Talaiot/"
            vcsUrl = "https://github.com/cdsap/Talaiot/"
            tags = listOf("kotlin", "gradle", "kotlin-dsl")
            (plugins) {
                "Talaiotx" {
                    displayName = "TalaiotBase"
                    description = "Simple and extensible plugin to track task and build times in your Gradle Project."
                    tags = listOf("tracking", "kotlin", "gradle")
                    version = versionTalaiot
                }
            }
        }
        //   target.extensions.getByType<JacocoPluginExtension::class.java>(){

        //  }
    }


}