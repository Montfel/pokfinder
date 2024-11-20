plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    api(projects.core.common.domain)

    ksp(libs.hilt.compiler)
    implementation(libs.hilt.core)
    implementation(libs.paging.common)
}