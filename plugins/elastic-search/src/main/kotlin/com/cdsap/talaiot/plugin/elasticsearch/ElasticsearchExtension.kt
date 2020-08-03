package com.cdsap.talaiot.plugin.elasticsearch
import com.cdsap.talaiot.base.extension.TalaiotExtension
import groovy.lang.Closure
import org.gradle.api.Project

class ElasticsearchExtension(project: Project) : TalaiotExtension(project) {
    /**
     * General Publisher configuration included in the build
     */
    var publishers: ElasticsearchConfiguration? = null

    fun publishers(block: ElasticsearchConfiguration.() -> Unit) {
        publishers = ElasticsearchConfiguration(project).also(block)
    }

    fun publishers(closure: Closure<*>) {
        publishers = ElasticsearchConfiguration(project)
        closure.delegate = publishers
        closure.call()
    }
}