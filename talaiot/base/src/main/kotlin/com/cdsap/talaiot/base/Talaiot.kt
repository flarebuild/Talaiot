package com.cdsap.talaiot.base

import com.cdsap.talaiot.base.listener.BuildCacheOperationListener
import com.cdsap.talaiot.base.extension.TalaiotExtension
import com.cdsap.talaiot.base.gradle.GradleBuildOperationManager
import com.cdsap.talaiot.base.listener.TalaiotListener
import com.cdsap.talaiot.base.provider.PublisherConfigurationProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
/**
 * Talaiot main [Plugin].
 *
 * Talaiot is a simple and extensible plugin for teams that use Gradle Build System. It stores information about
 * your Gradle tasks and helps you detect problems and bottlenecks of your builds. For every tracked task and build
 * it will add additional information defined by default and custom metrics
 * specified in [com.cdsap.talaiot.configuration.MetricsConfiguration].
 *
 * usage:
 * plugins {
 *   id("talaiot")
 * }
 */
class Talaiot <T : TalaiotExtension>(
    private val classExtension: Class<T>,
    private val pro: PublisherConfigurationProvider
)  {
    /**
     * Initialization of the plugin. The plugin needs to receive callbacks
     * from the [org.gradle.api.execution.TaskExecutionListener]
     * and [org.gradle.BuildListener] to start tracking the information of the tasks.
     *
     * Additionally we need the a list of metrics and providers that will be used during the execution.
     *
     * @param extension Talaiot extension that contains the configuration
     * @param project Gradle project used to to retrieve buildProperties and build information.
     */

    fun setUpPlugin(target: Project) {
        val extension = target.extensions.create("talaiot", classExtension, target)
        println("1")
        val buildOperationListener = BuildCacheOperationListener()
        println("2")
        val listener = TalaiotListener(
            target,
            extension,
            buildOperationListener,
            pro
        )
        println("#")
        target.gradle.addBuildListener(listener)
        GradleBuildOperationManager()
            .initOperationListener(target, buildOperationListener)
    }

}