package utils

import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

private const val KOVER = "kover"
private const val MIN_BOUND_VALUE = 0

internal fun Project.configureKoverForRootProject() {
    configureKover()

    dependencies {
        subprojects
            .filter { project -> project.subprojects.isEmpty() }
            .forEach { project -> add(KOVER, project) }
    }
}

internal fun Project.configureKover() {
    pluginManager.apply(libs.plugins.kover.get().pluginId)

    extensions.getByType<KoverProjectExtension>().apply {
        reports {
            filters {
                excludes {
                    packages("*.di", "*.dto", "*.model", "*.mapper", "*.util", "*.contract")
                }

                includes {
                    classes("*.*ViewModel")
                    packages("*.domain", "*.data")
                }
            }

            verify {
                rule {
                    minBound(MIN_BOUND_VALUE)
                }
            }
        }
    }
}
