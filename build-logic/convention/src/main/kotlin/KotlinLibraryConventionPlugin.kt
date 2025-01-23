import org.gradle.api.Plugin
import org.gradle.api.Project
import utils.configureKotlinLibrary
import utils.applyKotlinPlugins
import utils.configureDetekt

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyKotlinPlugins()

            configureKotlinLibrary()
            configureDetekt()
        }
    }
}
