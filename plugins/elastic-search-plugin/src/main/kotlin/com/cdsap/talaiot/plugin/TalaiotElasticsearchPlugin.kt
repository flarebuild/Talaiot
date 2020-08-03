package com.cdsap.talaiot.plugin

import com.cdsap.talaiot.base.Talaiot
import org.gradle.api.Plugin
import org.gradle.api.Project

class TalaiotElasticsearchPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        Talaiot(
            ElasticsearchExtension::class.java,
            ElasticsearchConfigurationProvider(
                target
            )
        ).setUpPlugin(target)
    }

}