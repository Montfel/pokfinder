import org.gradle.api.Plugin
import org.gradle.api.Project
import utils.applyKotlinPlugins
import utils.configureDetekt
import utils.configureKotlinLibrary
import utils.configureKover

class KotlinLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyKotlinPlugins()

            configureKotlinLibrary()
            configureDetekt()
            configureKover()
        }
    }
}
