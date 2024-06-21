plugins {
    alias(libs.plugins.android.application)
    id(libs.plugins.firebase.crashlytics.gradle.get().pluginId)
    id(libs.plugins.google.services.get().pluginId)
    id(libs.plugins.hilt.get().pluginId)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.montfel.pokfinder"
    compileSdk = ProjectConfig.compileSdkVersion

    defaultConfig {
        applicationId = "com.montfel.pokfinder"
        minSdk = ProjectConfig.minSdkVersion
        targetSdk = ProjectConfig.targetSdkVersion
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }

        release {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/gradle/incremental.annotation.processors"
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.presentation)
    implementation(projects.designsystem)

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