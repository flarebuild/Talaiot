package com.cdsap.talaiot.base.gradle

import com.cdsap.talaiot.base.listener.BuildCacheOperationListener
import org.gradle.api.Project
import org.gradle.api.internal.GradleInternal
import org.gradle.api.invocation.Gradle
import org.gradle.internal.operations.BuildOperationListenerManager

class GradleBuildOperationManager {
    fun initOperationListener(
        project: Project,
        buildCacheOperationListener: BuildCacheOperationListener
    ) {
        project.gradle.buildOperationListenerManager().addListener(buildCacheOperationListener)
        project.gradle.buildFinished {
            project.gradle.removeListener(buildCacheOperationListener)
        }
    }

    private fun Gradle.buildOperationListenerManager(): BuildOperationListenerManager =
        (this as GradleInternal).services[BuildOperationListenerManager::class.java]

}