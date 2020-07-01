package com.cdsap.base.publisher.provider

interface Provider<T> {
    fun get(): T
}
