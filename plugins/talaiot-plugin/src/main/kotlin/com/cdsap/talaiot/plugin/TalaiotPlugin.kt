package com.cdsap.talaiot.plugin


import com.cdsap.talaiot.base.Talaiot
import com.cdsap.talaiot.base.extension.TalaiotExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class TalaiotPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        Talaiot(
            TalaiotExtension::class.java,
            TalaiotConfigurationProvider(
                target
            )
        ).setUpPlugin(target)
    }

}