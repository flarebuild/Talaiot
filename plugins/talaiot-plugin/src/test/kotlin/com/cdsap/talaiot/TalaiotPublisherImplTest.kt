package com.cdsap.talaiot


import com.cdsap.talaiot.base.configuration.MetricsConfiguration

import com.cdsap.talaiot.base.entities.ExecutionReport
import com.cdsap.talaiot.base.entities.TaskLength
import com.cdsap.talaiot.base.entities.TaskMessageState
import com.cdsap.talaiot.base.logger.LogTrackerImpl
import com.cdsap.talaiot.base.entities.CacheInfo
import com.cdsap.talaiot.base.entities.ExecutedGradleTaskInfo
import com.cdsap.talaiot.base.entities.ExecutedTasksInfo
import com.cdsap.talaiot.base.provider.Provider
import com.cdsap.talaiot.base.publisher.TalaiotPublisherImpl
import com.cdsap.talaiot.plugin.TalaiotConfigurationProvider
import com.cdsap.talaiot.plugin.TalaiotPluginExtension
import com.cdsap.talaiot.publisher.base.OutputPublisherConfiguration
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer


class TalaiotPublisherImplTest : BehaviorSpec({

    given("TalaiotPublisher implementation") {
        val logger = LogTrackerImpl()

        `when`("publisher is included and one task is executed") {
            val project: Project = mock()
            val extension = TalaiotPluginExtension(project).apply {
                publishers {
                    outputPublisher
                }
                metricsConfiguration()
            }

            setUpMockExtension(project, extension)

            val publishers : TalaiotConfigurationProvider = TalaiotConfigurationProvider(project)
            val publisher = TalaiotPublisherImpl(
                extension, logger, getMetricsProvider(), publishers,
                ExecutedTasksInfo(emptyMap())
            )
            publisher.publish(
                mutableListOf(getSingleTask()),
                0,
                100,
                200,
                true
            )
            then("outputPublisher is publishing one task result ") {
                assert(publishers.get().size == 1)
                verify(publishers.get()[0]).publish(argThat {
                    this.tasks!!.size == 1
                })

            }
        }
        `when`("more than one publisher is included ") {
            val project: Project = mock()
            val extension = TalaiotPluginExtension(project).apply {
                publishers {
                    outputPublisher
                    influxDbPublisher {
                        dbName = "db"
                        url = ""
                        taskMetricName = ""
                    }
                }
                metricsConfiguration()
            }
            setUpMockExtension(project, extension)

            val publishers=  TalaiotConfigurationProvider(project)


            TalaiotPublisherImpl(
                extension, logger, getMetricsProvider(), publishers,
                ExecutedTasksInfo(emptyMap())
            ).publish(
                getTasks(),
                0,
                100,
                200,
                true
            )

            then("two publishers are processed ") {
                assert(publishers.get().size == 2)
                verify(publishers.get()[0]).publish(argThat {
                    this.tasks!!.size == 2
                })

            }
        }
        `when`("one filter has been configured") {
            val project: Project = mock()
            val extension = TalaiotPluginExtension(project).apply {
                filter {
                    tasks {
                        excludes = arrayOf("taskA")
                    }
                }
                publishers {
                    outputPublisher
                    influxDbPublisher {
                        dbName = "db"
                        url = ""
                        taskMetricName = ""
                    }
                }

                metricsConfiguration()
            }
            setUpMockExtension(project, extension)

            val publishers : TalaiotConfigurationProvider = TalaiotConfigurationProvider(project)



            TalaiotPublisherImpl(
                extension, logger, getMetricsProvider(), publishers,
                ExecutedTasksInfo(emptyMap())
            ).publish(
                getTasks(),
                0,
                100,
                200,
                true
            )

            then("two publishers are processed and one task has been filtered ") {
                assert(publishers.get().size == 2)
                verify(publishers.get()[0]).publish(argThat {
                    this.tasks!!.size == 1
                })
            }
        }

        `when`("one filter has been configured in project with two publishers") {
            val project: Project = mock()
            val extension = TalaiotPluginExtension(project).apply {
                filter {
                    tasks {
                        excludes = arrayOf("taskA")
                    }
                }
                publishers {
                    outputPublisher = OutputPublisherConfiguration()
                    influxDbPublisher {
                        dbName = "db"
                        url = ""
                        taskMetricName = ""
                    }
                }

                metricsConfiguration()
            }
            setUpMockExtension(project, extension)

            val publishers = TalaiotConfigurationProvider(project)

            TalaiotPublisherImpl(
                extension, logger, getMetricsProvider(), publishers,
                ExecutedTasksInfo(emptyMap())
            ).publish(
                getTasks(),
                0,
                100,
                200,
                true
            )

            then("two publishers are processed and one task has been filtered ") {
                assert(publishers.get().size == 1)
                verify(publishers.get()[0]).publish(argThat {
                    this.tasks!!.size == 1
                })
            }
        }

//        `when`("build filter configured to publish only successful build") {
//            val project: Project = mock()
//            val extension = TalaiotFatExtensionPlugin(project).apply {
//                filter {
//                    build {
//                        success = true
//                    }
//                }
//                publishers {
//                    jsonPublisher = true
//                }
//            }
//            setUpMockExtension(project, extension)
//
//            then("successful build is published") {
//                val publisher = TalaiotConfigurationProvider(project)
//
//
//                TalaiotPublisherImpl(
//                    extension,
//                    logger,
//                    getMetricsProvider(),
//                    publisher,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = true
//                )
//
//                verify(publisher).get().publish(any())
//            }
//
//            then("failed build is not published") {
//                val publisher = TalaiotConfigurationProvider(project)
//                val publisherProvider: Provider<List<Publisher>> =
//                    SimpleProvider(listOf(publisher))
//
//                TalaiotPublisherImpl(
//                    extension,
//                    logger,
//                    getMetricsProvider(),
//                    publisherProvider,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = false
//                )
//
//                verifyZeroInteractions(publisher)
//            }
//        }

//        `when`("build filter configured to exclude requested tasks") {
//            val project: Project = mock()
//            val extension = TalaiotFatExtensionPlugin(project).apply {
//                filter {
//                    build {
//                        requestedTasks {
//                            excludes = arrayOf(":module:taskA")
//                        }
//                    }
//                }
//            }
//            setUpMockExtension(project, extension)
//
//            then("build with a different task is published") {
//                val publisher = TalaiotConfigurationProvider(project)
//                val publisherProvider: Provider<List<Publisher>> =
//                    SimpleProvider(listOf(publisher))
//                val report = ExecutionReport(requestedTasks = ":module:taskB")
//
//                TalaiotPublisherImpl(
//                    extension,
//                    logger,
//                    SimpleProvider(report),
//                    publisherProvider,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = false
//                )
//
//                verify(publisher).publish(any())
//            }
//
//            then("build with all tasks filtered out is not published") {
//                val publisher = TalaiotConfigurationProvider(project)
//                val publisherProvider: Provider<List<Publisher>> =
//                    SimpleProvider(listOf(publisher))
//                val report = ExecutionReport(requestedTasks = ":module:taskA")
//
//                TalaiotPublisherImpl(
//                    extension,
//                    logger,
//                    SimpleProvider(report),
//                    publisherProvider,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = true
//                )
//
//                verifyZeroInteractions(publisher)
//            }
//        }
//
//        `when`("build filter configured to include requested tasks") {
//            val project: Project = mock()
//            val extension = TalaiotFatExtensionPlugin(project).apply {
//                filter {
//                    build {
//                        requestedTasks {
//                            includes = arrayOf(":module:taskA")
//                        }
//                    }
//                }
//            }
//            setUpMockExtension(project, extension)
//
//            then("build with a different task is not published") {
//                val publisher = TalaiotConfigurationProvider(project)
//                val publisherProvider: Provider<List<Publisher>> =
//                    SimpleProvider(listOf(publisher))
//                val report = ExecutionReport(requestedTasks = ":module:taskB")
//
//                TalaiotPublisherImpl(
//                    extension,
//                    logger,
//                    SimpleProvider(report),
//                    publisherProvider,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = false
//                )
//
//                verifyZeroInteractions(publisher)
//            }
//
//            then("build with the same task is published") {
//                val publisher = TalaiotConfigurationProvider(project)
//                val publisherProvider: Provider<List<Publisher>> =
//                    SimpleProvider(listOf(publisher))
//                val report = ExecutionReport(requestedTasks = ":module:taskA")
//
//                TalaiotPublisherImpl(
//                    extension,
//                    logger,
//                    SimpleProvider(report),
//                    publisherProvider,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = true
//                )
//
//                verify(publisher).publish(any())
//            }
//        }
//
//        `when`("build filter configured to include and exclude tasks") {
//            val project: Project = mock()
//            val extension = TalaiotFatExtensionPlugin(project).apply {
//                filter {
//                    build {
//                        success = true
//                        requestedTasks {
//                            excludes = arrayOf(":module:taskB")
//                            includes = arrayOf(":module:taskA")
//                        }
//                    }
//                }
//            }
//            setUpMockExtension(project, extension)
//
//            then("build with at least one task included is published") {
//                val publisher = TalaiotConfigurationProvider(project)
//                val publisherProvider: Provider<List<Publisher>> =
//                    SimpleProvider(listOf(publisher))
//                val report = ExecutionReport(requestedTasks = ":module:taskA :module:taskB")
//
//                TalaiotPublisherImpl(
//                    extension,
//                    logger,
//                    SimpleProvider(report),
//                    publisherProvider,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = true
//                )
//
//                verify(publisher).publish(any())
//            }
//
//            then("build with all tasks filtered out is not published") {
//                val publisher = TalaiotConfigurationProvider(project)
//                val publisherProvider: Provider<List<Publisher>> =
//                    SimpleProvider(listOf(publisher))
//                val report = ExecutionReport(requestedTasks = ":module:taskB")
//
//                TalaiotPublisherImpl(
//                    extension, logger,
//                    SimpleProvider(report), publisherProvider,
//                    ExecutedTasksInfo(emptyMap())
//                ).publish(
//                    taskLengthList = getTasks(),
//                    start = 0,
//                    configuraionMs = 100,
//                    end = 200,
//                    success = true
//                )
//
//                verifyZeroInteractions(publisher)
//            }
//        }

        `when`("tasks cache information is present") {
            val project: Project = mock()
            val extension = TalaiotPluginExtension(project).apply {
                publishers {
                    outputPublisher
                }
                metricsConfiguration()
            }

            setUpMockExtension(project, extension)

            val publishers : TalaiotConfigurationProvider = TalaiotConfigurationProvider(project)

            val publisher = TalaiotPublisherImpl(
                extension, logger, getMetricsProvider(), publishers,
                ExecutedTasksInfo(
                    mapOf(
                        ":app:a" to ExecutedGradleTaskInfo(
                            id = 1L,
                            taskName = ":app:a",
                            isCacheEnabled = false,
                            localCacheInfo = CacheInfo.CacheDisabled,
                            remoteCacheInfo = CacheInfo.CacheDisabled
                        ),
                        ":app:b" to ExecutedGradleTaskInfo(
                            id = 1L,
                            taskName = ":app:b",
                            isCacheEnabled = true,
                            localCacheInfo = CacheInfo.CacheHit,
                            remoteCacheInfo = CacheInfo.CacheDisabled
                        ),
                        ":app:c" to ExecutedGradleTaskInfo(
                            id = 1L,
                            taskName = ":app:c",
                            isCacheEnabled = true,
                            localCacheInfo = CacheInfo.CacheMiss,
                            remoteCacheInfo = CacheInfo.CacheHit
                        )
                    )
                )
            )
            publisher.publish(
                mutableListOf(
                    getSingleTask("a"),
                    getSingleTask("b"),
                    getSingleTask("c")
                ),
                0,
                100,
                200,
                true
            )
            then("should publish cache information for each task") {
                val reportCaptor = argumentCaptor<ExecutionReport>()
                verify(publishers.get()[0]).publish(reportCaptor.capture())

                val expectedTaskA = TaskLength(
                    ms=1,
                    taskName="a",
                    taskPath=":app:a",
                    state=TaskMessageState.EXECUTED,
                    rootNode=false,
                    module="app",
                    taskDependencies= emptyList(),
                    workerId="",
                    startMs=0,
                    stopMs=0,
                    critical=false,
                    isCacheEnabled=false,
                    isLocalCacheHit=false,
                    isLocalCacheMiss=false,
                    isRemoteCacheHit=false,
                    isRemoteCacheMiss=false
                )

                val expectedTasks = listOf(
                    expectedTaskA,
                    expectedTaskA.copy(
                        taskName = "b", taskPath = ":app:b",
                        isCacheEnabled = true,
                        isLocalCacheHit = true,
                        isLocalCacheMiss = false,
                        isRemoteCacheHit = false,
                        isRemoteCacheMiss = false
                    ),
                    expectedTaskA.copy(
                        taskName = "c", taskPath = ":app:c",
                        isCacheEnabled = true,
                        isLocalCacheHit = false,
                        isLocalCacheMiss = true,
                        isRemoteCacheHit = true,
                        isRemoteCacheMiss = false
                    )
                )
                reportCaptor.firstValue.tasks.shouldBe(expectedTasks)

            }
        }
    }

})

private fun setUpMockExtension(project: Project, extension: TalaiotPluginExtension) {
    val extensionContainer: ExtensionContainer = mock()
    whenever(project.extensions).thenReturn(extensionContainer)
    whenever(project.name).thenReturn("TestProject")
    whenever(extensionContainer.getByType(TalaiotPluginExtension::class.java)).thenReturn(extension)
    whenever(extensionContainer.getByName("talaiot")).thenReturn(extension)
}

private fun getMetricsProvider(report: ExecutionReport = ExecutionReport()): Provider<ExecutionReport> {
    return SimpleProvider(report)
}

private fun metricsConfiguration() = MetricsConfiguration()

private fun getTasks() = mutableListOf(
    getSingleTask(),
    TaskLength(
        ms = 1L,
        taskName = "taskA",
        taskPath = ":app:clean",
        state = TaskMessageState.EXECUTED,
        rootNode = false,
        module = "app",
        taskDependencies = emptyList()
    )
)

private fun getSingleTask(name: String = "a") = TaskLength(
    ms = 1L,
    taskName = name,
    taskPath = ":app:$name",
    state = TaskMessageState.EXECUTED,
    rootNode = false,
    module = "app",
    taskDependencies = emptyList()
)

private class SimpleProvider<T>(private val value: T) : Provider<T> {
    override fun get(): T {
        return value
    }
}
