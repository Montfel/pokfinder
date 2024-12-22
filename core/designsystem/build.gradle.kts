plugins {
    alias(libs.plugins.pokfinder.compose.library)
}

android {
    namespace = "${libs.versions.app.namespace.get()}.core.designsystem"
}

dependencies {
    implementation(libs.core)
    implementation(libs.coil)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.splash)

    testImplementation(libs.junit.test)
    androidTestImplementation(libs.junit.test.android)
    androidTestImplementation(libs.espresso)
}