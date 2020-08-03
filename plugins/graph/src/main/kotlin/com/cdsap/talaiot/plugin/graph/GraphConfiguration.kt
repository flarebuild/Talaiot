package com.cdsap.talaiot.plugin.graph

import com.cdsap.talaiot.base.publisher.PublishersConfiguration
import com.cdsap.talaiot.publisher.graph.TaskDependencyGraphConfiguration
import groovy.lang.Closure
import org.gradle.api.Project

class GraphConfiguration(project: Project) : PublishersConfiguration(project) {

    var taskDependencyGraphPublisher: TaskDependencyGraphConfiguration? = null

      /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param configuration Configuration block for the [OutputPublisherConfiguration]
     */
    fun taskDependencyGraphPublisher(configuration: TaskDependencyGraphConfiguration.() -> Unit) {
        taskDependencyGraphPublisher = TaskDependencyGraphConfiguration(project).also(configuration)
    }

    /**
     * Configuration accessor within the [PublishersConfiguration] for the [OutputPublisher]
     *
     * @param closure closure for the [OutputPublisherConfiguration]
     */
    fun taskDependencyGraphPublisher(closure: Closure<*>) {
        taskDependencyGraphPublisher = TaskDependencyGraphConfiguration(project)
        closure.delegate = taskDependencyGraphPublisher
        closure.call()
    }
}