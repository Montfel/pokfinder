plugins {
    id(libs.plugins.firebase.crashlytics.gradle.get().pluginId)
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)

    alias(libs.plugins.pokfinder.android.application)
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
    implementation(projects.core.network)

    implementation(projects.feature.home.data)
    implementation(projects.feature.home.ui)
    implementation(projects.feature.profile.data)
    implementation(projects.feature.profile.ui)

    implementation(libs.activity)
    implementation(libs.core)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.hilt.android)
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    implementation(libs.kotlin.serialization.core)
    implementation(libs.navigation.runtime)
    implementation(libs.navigation.ui)
    implementation(libs.bundles.review)
    implementation(libs.splash)
}
