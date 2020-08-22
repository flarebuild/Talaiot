package com.cdsap.talaiot.plugin.rethinkdb

import com.cdsap.talaiot.base.extension.TalaiotExtension
import groovy.lang.Closure
import org.gradle.api.Project


open class RethinkDbExtension(project: Project) : TalaiotExtension(project) {
    /**
     * General Publisher configuration included in the build
     */
    var publishers: RethinkDbConfiguration? = null

    fun publishers(block: RethinkDbConfiguration.() -> Unit) {
        publishers = RethinkDbConfiguration(project).also(block)
    }

    fun publishers(closure: Closure<*>) {
        publishers = RethinkDbConfiguration(project)
        closure.delegate = publishers
        closure.call()
    }
}
