plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)

    alias(libs.plugins.pokfinder.kotlin.library)
}

dependencies {
    api(projects.core.common.domain)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.core)
}