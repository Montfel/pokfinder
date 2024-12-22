import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import utils.configureAndroidLibrary
import utils.libs

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.android.library.get().pluginId)
            apply(plugin = libs.plugins.kotlin.android.get().pluginId)

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
            }
        }
    }
}
