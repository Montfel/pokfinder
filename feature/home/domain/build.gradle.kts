plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(projects.core.common.domain)

    implementation(libs.hilt.compiler)
    implementation(libs.paging.common)
}