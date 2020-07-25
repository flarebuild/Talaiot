package com.cdsap.talaiot.publisher.graph

import com.cdsap.talaiot.base.configuration.IgnoreWhenConfiguration
import com.cdsap.talaiot.base.configuration.PublisherConfiguration
import groovy.lang.Closure
import org.gradle.api.Project

/**
 * Configuration for [com.cdsap.talaiot.publisher.TaskDependencyGraphPublisher]
 * taskDependencyGraphPublisher{
 *    gexf = true
 *    html = false
 *    dot = true
 *    ignoreWhen{
 *       envName = "CI"
 *       envValue = "false"
 *    }
 * }
 */
class TaskDependencyGraphConfiguration(val project: Project) : PublisherConfiguration {
    override var name: String = "output"
    override var publishBuildMetrics: Boolean = true
    override var publishTaskMetrics: Boolean = true

    var ignoreWhen: IgnoreWhenConfiguration? = null
    /**
     * Flag to enable [com.cdsap.talaiot.publisher.graphpublisher.GexfPublisher]
     */
    var gexf = false
    /**
     * Flag to enable [com.cdsap.talaiot.publisher.graphpublisher.HtmlPublisher]
     */
    var html = false
    /**
     * Flag to enable [com.cdsap.talaiot.publisher.graphpublisher.DotPublisher]
     */
    var dot = false

    /**
     * Kotlin accessor for the [IgnoreWhenConfiguration]
     *
     * @param block Lambda with receiver for the [IgnoreWhenConfiguration]
     */
    fun ignoreWhen(block: IgnoreWhenConfiguration.() -> Unit) {
        ignoreWhen = IgnoreWhenConfiguration(project).also(block)
    }

    /**
     * Groovy accessor for the [IgnoreWhenConfiguration]
     *
     * @param closure closure for the [IgnoreWhenConfiguration]
     */
    fun ignoreWhen(closure: Closure<*>) {
        ignoreWhen = IgnoreWhenConfiguration(project)
        closure.delegate = ignoreWhen
        closure.call()
    }
}