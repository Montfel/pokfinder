package utils

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

internal fun configureCompose(commonExtension: CommonExtension<*, *, *, *, *, *>,) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }
}

internal fun Project.applyComposePlugins() {
    apply(plugin = libs.plugins.compose.compiler.get().pluginId)
}