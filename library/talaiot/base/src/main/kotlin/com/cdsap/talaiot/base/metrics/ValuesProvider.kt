package com.cdsap.talaiot.base.metrics

interface ValuesProvider {
    fun get(): Map<String, Any>
}
