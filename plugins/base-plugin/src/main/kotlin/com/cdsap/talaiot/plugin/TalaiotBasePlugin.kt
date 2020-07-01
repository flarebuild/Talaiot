package com.cdsap.talaiot.plugin

import com.cdsap.base.publisher.BuildCacheOperationListener
import com.cdsap.base.publisher.TalaiotListener
import com.cdsap.base.publisher.logger.LogTrackerImpl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.internal.GradleInternal
import org.gradle.api.invocation.Gradle
import org.gradle.internal.operations.BuildOperationListenerManager

class TalaiotBasePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val extension: BaseExtension = target.extensions.create("talaiot", BaseExtension::class.java, target)
        initPlugin(extension, target)
    }

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
    private fun initPlugin(extension: BaseExtension, project: Project) {
        val buildOperationListener = BuildCacheOperationListener()

        val listener = TalaiotListener(
            project,
            extension,
            buildOperationListener,
            BaseConfigurationProvider(project, LogTrackerImpl())
        )
        project.gradle.addBuildListener(listener)
        project.gradle.buildOperationListenerManager().addListener(buildOperationListener)
        project.gradle.buildFinished {
            project.gradle.removeListener(buildOperationListener)
        }
    }

    private fun Gradle.buildOperationListenerManager(): BuildOperationListenerManager =
        (this as GradleInternal).services[BuildOperationListenerManager::class.java]
}