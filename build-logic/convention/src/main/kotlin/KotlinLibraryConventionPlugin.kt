import org.gradle.api.Plugin
import org.gradle.api.Project
import utils.configureKotlinLibrary
import org.gradle.kotlin.dsl.apply
import utils.libs

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.kotlin.jvm.get().pluginId)

            configureKotlinLibrary()
        }
    }
}
