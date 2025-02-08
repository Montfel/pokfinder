import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import utils.applyAndroidApplicationPlugins
import utils.configureAndroidApplication
import utils.configureCompose
import utils.configureDetekt

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyAndroidApplicationPlugins()

            extensions.configure<ApplicationExtension> {
                configureAndroidApplication(this)
                configureCompose(this)
                configureDetekt()
            }
        }
    }
}
