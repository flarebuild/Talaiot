package com.cdsap.talaiot.plugin.pushgateway

import com.cdsap.talaiot.base.extension.TalaiotExtension
import groovy.lang.Closure
import org.gradle.api.Project


open class PushGatewayExtension(project: Project) : TalaiotExtension(project) {
    /**
     * General Publisher configuration included in the build
     */
    var publishers: PushGatewayConfiguration? = null

    fun publishers(block: PushGatewayConfiguration.() -> Unit) {
        publishers = PushGatewayConfiguration(project).also(block)
    }

    fun publishers(closure: Closure<*>) {
        publishers = PushGatewayConfiguration(project)
        closure.delegate = publishers
        closure.call()
    }
}
