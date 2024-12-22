import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import utils.configureAndroidLibrary
import utils.configureCompose
import utils.libs

class ComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.android.library.get().pluginId)
            apply(plugin = libs.plugins.kotlin.android.get().pluginId)
            apply(plugin = libs.plugins.compose.compiler.get().pluginId)

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
                configureCompose(this)
            }
        }
    }
}
