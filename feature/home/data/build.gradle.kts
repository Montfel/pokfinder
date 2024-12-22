plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)

    alias(libs.plugins.pokfinder.android.library)
}

android {
    namespace = "${libs.versions.app.namespace.get()}.feature.home.data"
}

dependencies {
    implementation(projects.core.database)
    implementation(projects.core.network)

    implementation(projects.feature.home.domain)

    implementation(libs.apollo)
    implementation(libs.core)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.paging.runtime)
    implementation(libs.bundles.retrofit)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    implementation(libs.room.runtime)

    testImplementation(libs.junit.test)
    androidTestImplementation(libs.junit.test.android)
    androidTestImplementation(libs.espresso)
}