plugins {
    alias(libs.plugins.android.application)
    id(libs.plugins.firebase.crashlytics.gradle.get().pluginId)
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)

    alias(libs.plugins.pokfinder.android.application)
}

dependencies {
    implementation(projects.core.common.domain)
    implementation(projects.core.database)
    implementation(projects.core.designsystem)
    implementation(projects.core.network)

    implementation(projects.feature.home.data)
    implementation(projects.feature.home.ui)
    implementation(projects.feature.profile.data)
    implementation(projects.feature.profile.ui)

    implementation(libs.activity)
    implementation(libs.core)
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation)
    implementation(libs.bundles.lifecycle)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.navigation)
    implementation(libs.bundles.review)
    implementation(libs.splash)

    testImplementation(libs.truth)
    testImplementation(libs.junit.test)
    androidTestImplementation(libs.junit.test.android)
}