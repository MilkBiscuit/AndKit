plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.kapt")
    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "com.cheng.andkit"
    compileSdk = 34

    defaultConfig {
        minSdk = 26
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-project.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Kotlin Coroutine
    implementation(libs.kotlinx.coroutines.android)
    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)
    // Security cryptography
    implementation(libs.androidx.security.crypto)
    // Retrofit
    implementation(libs.retrofit)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.roboletric)
    androidTestImplementation(libs.android.test.androidx.junit)
    androidTestImplementation(libs.android.test.androidx.espresso.core)
}
