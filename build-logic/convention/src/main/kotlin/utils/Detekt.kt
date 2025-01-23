package utils

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.withType

internal fun Project.configureDetekt() {
    pluginManager.apply(libs.gradle.plugin.detekt.get().group)
    val extension = extensions.getByType<DetektExtension>()

    with(extension) {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom("$rootDir/detekt/detekt.yml")

        tasks.named<Detekt>("detekt") {
            reports {
                xml.required.set(true)
                html.required.set(true)
                txt.required.set(true)
                sarif.required.set(true)
                md.required.set(true)
            }
        }

        tasks.withType<Detekt>().configureEach {
            jvmTarget = JavaVersion.VERSION_21.toString()
        }
        tasks.withType<DetektCreateBaselineTask>().configureEach {
            jvmTarget = JavaVersion.VERSION_21.toString()
        }
    }
}
