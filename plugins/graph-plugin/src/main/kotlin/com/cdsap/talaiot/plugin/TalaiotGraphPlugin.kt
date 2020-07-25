package com.cdsap.talaiot.plugin

import com.cdsap.talaiot.base.Talaiot
import org.gradle.api.Plugin
import org.gradle.api.Project

class TalaiotGraphPlugin : Plugin<Project> {

    override fun apply(target: Project) {
       Talaiot(
            GraphExtension::class.java,
            GraphConfigurationProvider(
                target
            )
        ).setUpPlugin(target)
    }
}