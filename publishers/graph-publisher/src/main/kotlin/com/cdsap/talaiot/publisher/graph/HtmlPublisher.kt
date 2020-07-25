package com.cdsap.talaiot.publisher.graph

import com.cdsap.talaiot.base.entities.ExecutionReport
import com.cdsap.talaiot.base.entities.TaskMessageState
import com.cdsap.talaiot.base.logger.LogTracker
import com.cdsap.talaiot.publisher.graph.resources.ResourcesHtml
import com.cdsap.talaiot.publisher.graph.resources.ResourcesHtml.LEGEND_HEADER
import com.cdsap.talaiot.publisher.graph.writer.FileWriter
import java.util.concurrent.Executor

/**
 * Publisher used to publish with the Html format.
 */
class HtmlPublisher(
    override var logTracker: LogTracker,
    override var fileWriter: FileWriter,
    /**
     * Executor to schedule a task in Background
     */
    private val executor: Executor
) : DefaultDiskPublisher(logTracker, fileWriter) {

    private val TAG = "HtmlPublisher"

    /**
     * name of the file generated by the publisher
     */
    private val fileName: String = "htmlTaskDependency.html"

    override fun publish(report: ExecutionReport) {
        executor.execute {
            val content = contentComposer(
                task = buildGraph(report),
                legend = legend(report),
                header = ResourcesHtml.HEADER,
                footer = ResourcesHtml.FOOTER
            )
            logTracker.log(TAG, "writing file")
            writeFile(content, fileName)
        }
    }

    override fun formatNode(
        internalId: Int,
        module: String,
        taskName: String,
        numberDependencies: Int,
        taskMessageState: TaskMessageState
    ): String = "      nodes.push({id: $internalId, title:'$taskName', group:'$module', " +
            "label: '$taskName', " +
            "value: $numberDependencies});\n"

    override fun formatEdge(
        from: Int,
        to: Int?
    ): String = "      edges.push({from: $from, to: $to});\n"

    /**
     * In the HtmlPublisher we want to show a legend representing the different colors of the modules.
     *
     * @param taskMeasurementAggregated Aggregated entity with the results of the build
     *
     * @return the aggregated legend for all the modules
     */
    private fun legend(report: ExecutionReport): String {
        var count = 10000
        var nodes = LEGEND_HEADER
        report.tasks?.distinctBy {
            it.module
        }?.forEach {
            count++
            nodes += "      nodes.push({id: $count, x: x, y: y, label: '${it.module}', group: '${it.module}', value: 1, " +
                    "fixed: true, physics:false});\n"
        }
        return nodes
    }


}
