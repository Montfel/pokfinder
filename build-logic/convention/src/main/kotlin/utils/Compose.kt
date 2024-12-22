package utils

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }
    }
}