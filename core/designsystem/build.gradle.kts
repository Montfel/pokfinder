plugins {
    alias(libs.plugins.pokfinder.compose.library)
}

android {
    namespace = "${libs.versions.app.namespace.get()}.core.designsystem"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.splash)
}