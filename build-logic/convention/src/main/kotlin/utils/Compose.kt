package utils

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

internal fun configureCompose(libraryExtension: LibraryExtension) {
    libraryExtension.apply {
        buildFeatures {
            compose = true
        }
    }
}

internal fun Project.applyComposePlugins() {
    apply(plugin = libs.plugins.compose.compiler.get().pluginId)
}
