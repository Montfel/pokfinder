import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `kotlin-dsl`
}

group = "com.montfel.pokfinder.buildlogic"

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.detekt)
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.kover)

    @Suppress("USELESS_CAST")
    implementation(
        files((libs as LibrariesForLibs).javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = libs.plugins.pokfinder.android.application.get().pluginId
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.pokfinder.android.library.get().pluginId
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("composeLibrary") {
            id = libs.plugins.pokfinder.compose.library.get().pluginId
            implementationClass = "ComposeLibraryConventionPlugin"
        }
        register("kotlinLibrary") {
            id = libs.plugins.pokfinder.kotlin.library.get().pluginId
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}
