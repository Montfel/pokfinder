import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import utils.applyAndroidPlugins
import utils.applyComposePlugins
import utils.configureAndroidLibrary
import utils.configureCompose

class ComposeLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyAndroidPlugins()
            applyComposePlugins()

            extensions.configure<LibraryExtension> {
                configureAndroidLibrary(this)
                configureCompose(this)
            }
        }
    }
}
