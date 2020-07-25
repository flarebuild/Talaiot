package com.cdsap.talaiot.base.configuration

import com.cdsap.talaiot.base.filter.StringFilter
import groovy.lang.Closure

class BuildFilterConfiguration {
    var success: Boolean? = null
    var requestedTasks: StringFilter = StringFilter()

    fun requestedTasks(configuration: StringFilter.() -> Unit) {
        requestedTasks.also(configuration)
    }

    fun requestedTasks(closure: Closure<*>) {
        closure.delegate = requestedTasks
        closure.call()
    }
}
