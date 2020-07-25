package com.cdsap.talaiot.plugin
import com.cdsap.talaiot.utils.TemporaryFolder
import io.kotlintest.specs.BehaviorSpec
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome

class RethinkDbPublisherBuildTest : BehaviorSpec({
    given("Build Gradle File") {
        val testProjectDir = TemporaryFolder()
        `when`("Talaiot is included with RethinkDbPublisher") {
            testProjectDir.create()
            val buildFile = testProjectDir.newFile("build.gradle")
            buildFile.appendText(
                """
                   plugins {
                      id 'java'
                      id 'com.cdsap.talaiot.plugins.rethinkdb'
                   }

                  talaiot{
                      logger = com.cdsap.talaiot.base.logger.LogTracker.Mode.INFO
                      publishers {
                         rethinkDbPublisher {
                             url = "http://localhost"
                             dbName = "tracking"
                    }
                }
            }
            """
            )
            val result = GradleRunner.create()
                .withProjectDir(testProjectDir.getRoot())
                .withArguments("assemble")
                .withPluginClasspath()
                .build()
            println(result.output)
            then("logs are shown in the output and including the RethinkDb format") {
                assert(result.output.contains(":assemble"))
                assert(result.task(":assemble")?.outcome == TaskOutcome.SUCCESS)

            }
            testProjectDir.delete()
        }
    }
})
