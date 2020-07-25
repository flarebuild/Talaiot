package com.cdsap.talaiot.plugin


import com.cdsap.talaiot.base.Talaiot
import org.gradle.api.Plugin
import org.gradle.api.Project

class TalaiotInfluxdbPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        Talaiot(
            InfluxdbExtension::class.java,
            InfluxdbConfigurationProvider(
                target
            )
        ).setUpPlugin(target)
    }

}