plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)

    alias(libs.plugins.pokfinder.android.library)
}

android {
    namespace = "${libs.versions.app.namespace.get()}.feature.profile.data"
}

dependencies {
    implementation(projects.core.database) //fixme foi colocado devido ao TypeDto estar no database
    implementation(projects.core.network)

    implementation(projects.feature.profile.domain)

    implementation(libs.gson)
    implementation(libs.apollo)
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    implementation(libs.retrofit)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
}
