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
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    implementation(libs.kotlin.coroutines)
    implementation(libs.paging.common)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
}