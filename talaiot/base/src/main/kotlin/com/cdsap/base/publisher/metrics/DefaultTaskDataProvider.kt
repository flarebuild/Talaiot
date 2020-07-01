package com.cdsap.talaiot.metrics

import com.cdsap.base.publisher.ValuesProvider
import com.cdsap.base.publisher.entities.ExecutionReport
import com.cdsap.base.publisher.entities.TaskLength

class DefaultTaskDataProvider(
    private val task: TaskLength,
    private val report: ExecutionReport
) : ValuesProvider {
    override fun get(): Map<String, Any> {
        return mapOf(
            "state" to task.state.name,
            "module" to task.module,
            "rootNode" to task.rootNode.toString(),
            "task" to task.taskPath,
            "workerId" to task.workerId,
            "critical" to task.critical.toString(),
            "value" to task.ms,
            "cacheEnabled" to task.isCacheEnabled,
            "localCacheHit" to task.isLocalCacheHit,
            "localCacheMiss" to task.isLocalCacheMiss,
            "remoteCacheHit" to task.isRemoteCacheHit,
            "remoteCacheMiss" to task.isRemoteCacheHit

        ) + report.customProperties.taskProperties
    }
}
