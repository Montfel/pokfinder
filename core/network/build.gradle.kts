plugins {
    alias(libs.plugins.apollo)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)

    alias(libs.plugins.pokfinder.android.library)
}

android {
    namespace = "${libs.versions.app.namespace.get()}.core.network"

    apollo {
        service("service") {
            packageName.set("${libs.versions.app.namespace.get()}.core.network")
        }
    }
}

dependencies {
    implementation(projects.core.common.domain)

    implementation(libs.apollo)
    implementation(libs.core)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(platform(libs.okhttp.bom))
    implementation(libs.bundles.okhttp)
    implementation(libs.kotlin.coroutines)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.bundles.retrofit)

    testImplementation(libs.junit.test)
    androidTestImplementation(libs.junit.test.android)
    androidTestImplementation(libs.espresso)
}