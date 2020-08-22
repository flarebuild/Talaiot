package com.cdsap.talaiot.base.provider

interface Provider<T> {
    fun get(): T
}
