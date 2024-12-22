import org.gradle.accessors.dm.LibrariesForLibs

plugins {
    `kotlin-dsl`
}

group = "com.montfel.pokfinder.buildlogic"

dependencies {
    compileOnly(libs.gradle.plugin.android)
    compileOnly(libs.gradle.plugin.kotlin)

    @Suppress("USELESS_CAST")
    implementation(
        files((libs as LibrariesForLibs).javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "pokfinder.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "pokfinder.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "pokfinder.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "pokfinder.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
    }
}