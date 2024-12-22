import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import utils.applyAndroidLibraryPlugins
import utils.applyComposePlugins
import utils.configureAndroidLibrary
import utils.configureCompose

class ComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyAndroidLibraryPlugins()
            applyComposePlugins()

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
                configureCompose(this)
            }
        }
    }
}
