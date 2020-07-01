import com.cdsap.base.publisher.logger.LogTrackerImpl
import com.cdsap.base.publisher.provider.PublisherConfigurationProvider
import com.cdsap.base.publisher.publisher.Publisher
import org.gradle.api.Project

class ElasticsearchConfigurationProvider(
    val project: Project,
    logTrackerImpl: LogTrackerImpl
) : PublisherConfigurationProvider {


    override fun get(): List<Publisher> {
        val publishers = mutableListOf<Publisher>()
        val talaiotExtension = project.extensions.getByName("talaiot") as ElasticsearchExtension

//        talaiotExtension.publishers?.apply {
//
//        }

    }