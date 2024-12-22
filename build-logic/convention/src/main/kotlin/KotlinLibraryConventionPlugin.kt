import org.gradle.api.Plugin
import org.gradle.api.Project
import utils.configureKotlinLibrary
import utils.applyKotlinPlugins

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyKotlinPlugins()

            configureKotlinLibrary()
        }
    }
}
