import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import utils.applyAndroidLibraryPlugins
import utils.configureAndroidLibrary

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyAndroidLibraryPlugins()

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
            }
        }
    }
}
