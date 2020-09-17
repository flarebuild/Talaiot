package com.talaiot.buildplugins

import org.gradle.api.Project
import org.gradle.api.attributes.LibraryElements
import org.gradle.api.file.CopySpec
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*
import java.net.URI

fun Project.setUpPublishing(
    namePublication: String,
    configurationArtifactId: String?
) {
    val a = this.tasks.getByName("jar") as Jar
//    a.configure<Jar> {
//        from(configurations.getByName("runtimeClasspath").incoming.artifactView {
//            attributes.attribute(LibraryElements.LIBRARY_ELEMENTS_ATTRIBUTE, objects.named(LibraryElements.CLASSES))
//        }.files.filter { it.isDirectory })



//        configure<Javadoc>{
//           classpath = configurations.getByName("runtimeClasspath")
//            // Be lenient as third party dependencies to not offer their source code in a folder (and we do now want to include these in our Javadoc)
//            source(sourcesPath.incoming.artifactView { lenient(true) }.files)
//        }
//        named<Jar>("sourcesJar").configure {
//            // Be lenient as third party dependencies to not offer their source code in a folder (and we do not want to package it)
//            from(sourcesPath.incoming.artifactView { lenient(true) }.files)
//        }files
  //  }



    this.configure<PublishingExtension> {
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
            create<MavenPublication>(namePublication) {
                groupId = group.toString()
                artifactId = getArtifact(configurationArtifactId, project)
                version = version.toString()
                from(components["java"])
                versionMapping {
                    usage("java-api") {
                        fromResolutionOf("runtimeClasspath")
                    }
                    usage("java-runtime") {
                        fromResolutionResult()
                    }
                }
            }
        }
    }
}

fun Project.setProjectGroup(
    configurationGroup: String?,
    default: String
) {
    group = configurationGroup ?: default
}

fun Project.setProjectGroup(
    default: String
) {
    group = default
}

fun Project.setProjectVersion(configurationVersion: String?) {
    version = configurationVersion ?: Constants.TALAIOT_VERSION
}

fun Project.setProjectVersion() {
    version = Constants.TALAIOT_VERSION
}

fun getArtifact(configurationArtifactId: String?, project: Project) =
    configurationArtifactId ?: project.name
