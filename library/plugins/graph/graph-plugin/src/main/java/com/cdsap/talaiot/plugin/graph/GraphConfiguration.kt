package com.cdsap.talaiot.plugin.graph

import com.cdsap.talaiot.publisher.PublishersConfiguration
import com.cdsap.talaiot.publisher.graph.TaskDependencyGraphConfiguration
import com.cdsap.talaiot.publisher.graph.TaskDependencyGraphPublisher
import groovy.lang.Closure
import org.gradle.api.Project

class GraphConfiguration(project: Project) : PublishersConfiguration(project) {
    internal var taskDependencyGraphPublisher: TaskDependencyGraphConfiguration? = null

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [TaskDependencyGraphPublisher].
     *
     * @param configuration Configuration block for the [TaskDependencyGraphConfiguration].
     */
    fun taskDependencyGraphPublisher(configuration: TaskDependencyGraphConfiguration.() -> Unit) {
        taskDependencyGraphPublisher = TaskDependencyGraphConfiguration(project).also(configuration)
    }

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [TaskDependencyGraphPublisher].
     *
     * @param closure Closure for the [TaskDependencyGraphConfiguration].
     */
    fun taskDependencyGraphPublisher(closure: Closure<*>) {
        taskDependencyGraphPublisher = TaskDependencyGraphConfiguration(project)
        closure.delegate = taskDependencyGraphPublisher
        closure.call()
    }
}
