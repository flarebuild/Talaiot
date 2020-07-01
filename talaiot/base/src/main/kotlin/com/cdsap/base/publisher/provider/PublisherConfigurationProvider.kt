package com.cdsap.base.publisher.provider

import com.cdsap.base.publisher.publisher.Publisher

interface PublisherConfigurationProvider {
    fun get(): List<Publisher>
}