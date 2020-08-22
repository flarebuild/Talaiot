package com.cdsap.talaiot.plugin.graph

import com.cdsap.talaiot.base.extension.TalaiotExtension
import groovy.lang.Closure
import org.gradle.api.Project


open class GraphExtension(project: Project) : TalaiotExtension(project) {
    /**
     * General Publisher configuration included in the build
     */
    var publishers: GraphConfiguration? = null

    fun publishers(block: GraphConfiguration.() -> Unit) {
        publishers = GraphConfiguration(project).also(block)
    }

    fun publishers(closure: Closure<*>) {
        publishers = GraphConfiguration(project)
        closure.delegate = publishers
        closure.call()
    }
}
