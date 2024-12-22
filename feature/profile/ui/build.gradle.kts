plugins {
    alias(libs.plugins.ksp)

    alias(libs.plugins.pokfinder.compose.library)
}

android {
    namespace = "${libs.versions.app.namespace.get()}.feature.home.ui"
}

dependencies {
    implementation(projects.core.designsystem)

    implementation(projects.feature.profile.domain)

    implementation(libs.core)
    implementation(libs.coil)
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation)
    ksp(libs.hilt.compiler)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.paging)

    testImplementation(libs.junit.test)
    androidTestImplementation(libs.junit.test.android)
    androidTestImplementation(libs.espresso)
}