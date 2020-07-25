package com.cdsap.talaiot.base.provider

import com.cdsap.talaiot.base.Publisher


interface PublisherConfigurationProvider {
    fun get(): List<Publisher>
}