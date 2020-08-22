package com.cdsap.talaiot.plugin.pushgateway

import com.cdsap.talaiot.base.Talaiot
import org.gradle.api.Plugin
import org.gradle.api.Project

class TalaiotPushGatewayPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        Talaiot(
            PushGatewayExtension::class.java,
            PushGatewayConfigurationProvider(
                target
            )
        ).setUpPlugin(target)
    }
}
