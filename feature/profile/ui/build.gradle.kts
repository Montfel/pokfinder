plugins {
    alias(libs.plugins.ksp)

    alias(libs.plugins.pokfinder.compose.library)
}

android {
    namespace = "${libs.versions.app.namespace.get()}.feature.profile.ui"
}

dependencies {
    implementation(projects.core.designsystem)

    implementation(projects.feature.profile.domain)

    implementation(libs.coil)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.compose)
    implementation(libs.lifecycle.compose)
}
