package com.cdsap.talaiot.plugin.com.cdsap.talaiot.plugin

import com.cdsap.talaiot.base.Talaiot
import com.cdsap.talaiot.plugin.ElasticsearchConfigurationProvider
import com.cdsap.talaiot.plugin.ElasticsearchExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class TalaiotElasticsearchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        println("asassaassasasaassa")
        Talaiot(
            ElasticsearchExtension::class.java,
            ElasticsearchConfigurationProvider(
                target
            )
        ).setUpPlugin(target)
    }

}