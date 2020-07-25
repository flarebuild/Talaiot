package com.cdsap.talaiot.base.metrics

import com.cdsap.talaiot.base.entities.*
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import junit.framework.Assert.assertTrue

class DefaultBuildMetricsProviderTest : BehaviorSpec({
    given("DefaultBuildMetricsProvider instance") {
        `when`("Environment includes just the build Information") {
            val metrics = DefaultBuildMetricsProvider(
                ExecutionReport(
                success = true,
                configurationDurationMs = "20",
                durationMs = "10"
            )
            ).get()
            then("no additional metrics are registered") {
                assertTrue(metrics.filter {
                    it.key == "duration" && it.value == 10L
                }.count() == 1)
                assertTrue(metrics.filter {
                    it.key == "configuration" && it.value == 20L
                }.count() == 1)
                assertTrue(metrics.filter {
                    it.key == "success" && it.value == true
                }.count() == 1)
                assertTrue(metrics.filter {
                    it.key == "osVersion"
                }.count() == 0)

            }
        }
        `when`("Complete execution Report") {
            val metrics = DefaultBuildMetricsProvider(completeExecutionReport()).get()

            then("all values are registered") {
                val expectedMap: Map<String, Any> = mapOf(
                    "start" to "1.590661991331E12".toDouble(),
                    "duration" to 10L,
                    "configuration" to 32L,
                    "success" to true,
                    "buildId" to "12",
                    "rootProject" to "app",
                    "requestedTasks" to "app:assembleDebug",
                    "scanLink" to "www.scan.link",
                    "buildInvocationId" to "123",
                    "osVersion" to "Linux 1.4",
                    "maxWorkers" to 2.toInt(),
                    "javaRuntime" to "1.2",
                    "cpuCount" to 4.toInt(),
                    "locale" to "EN-us",
                    "username" to "user",
                    "publicIp" to "127.0.0.1",
                    "defaultCharset" to "default",
                    "ideVersion" to "2.1",
                    "gradleVersion" to "6.2.2",
                    "gitBranch" to "git_branch",
                    "gitUser" to "git_user",
                    "hostname" to "localMachine",
                    "osManufacturer" to "osManufact4r",
                    "cacheMode" to "cacheMode",
                    "cachePushEnabled" to "true",
                    "cacheUrl" to "cacheUrl",
                    "cacheStore" to "10",
                    "localCacheHit" to "1",
                    "localCacheMiss" to "0",
                    "remoteCacheHit" to "0",
                    "remoteCacheMiss" to "0",
                    "switch.daemon" to "true",
                    "switch.offline" to "true",
                    "metric3" to "value3",
                    "metric4" to "value4"
                )
                metrics.shouldBe(expectedMap)
            }
        }
    }
})

fun completeExecutionReport() = ExecutionReport(
    beginMs = "1.590661991331E12",
    endMs = "1243",
    durationMs = "10",
    buildId = "12",
    rootProject = "app",
    requestedTasks = "app:assembleDebug",
    scanLink = "www.scan.link",
    buildInvocationId = "123",
    configurationDurationMs = "32",
    environment = Environment(
        cpuCount = "4",
        osVersion = "Linux 1.4",
        maxWorkers = "2",
        javaRuntime = "1.2",
        locale = "EN-us",
        username = "user",
        publicIp = "127.0.0.1",
        defaultChartset = "default",
        ideVersion = "2.1",
        gradleVersion = "6.2.2",
        cacheMode = "cacheMode",
        cachePushEnabled = "true",
        cacheUrl = "cacheUrl",
        cacheStore = "10",
        localCacheHit = 1,
        localCacheMiss = 0,
        remoteCacheHit = 0,
        remoteCacheMiss = 0,
        gitBranch = "git_branch",
        gitUser = "git_user",
        switches = Switches(
            daemon = "true",
            offline = "true"
        ),
        hostname = "localMachine",
        osManufacturer = "osManufact4r"
    ),
    success = true,
    customProperties = CustomProperties(
        taskProperties = getMetricsTasks(),
        buildProperties = getMetricsBuild()
    ),
    tasks = listOf(
        TaskLength(
            1, "clean", ":clean", TaskMessageState.EXECUTED, false,
            "app", emptyList()
        )
    )
)

fun getMetricsTasks(): MutableMap<String, String> {
    return mutableMapOf(
        "metric1" to "value1",
        "metric2" to "value2"
    )
}

fun getMetricsBuild(): MutableMap<String, String> {
    return mutableMapOf(
        "metric3" to "value3",
        "metric4" to "value4"
    )
}