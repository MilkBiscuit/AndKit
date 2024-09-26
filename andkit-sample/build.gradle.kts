plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "2.0.20"
}

android {
    namespace = "com.cheng.andkit.sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cheng.andkit.sample"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
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
    implementation(project(":andkit"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.material)

    // Kotlin Coroutine
    implementation(libs.kotlinx.coroutines.android)
    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.kotlinx.coroutines.test)
    androidTestImplementation(libs.android.test.androidx.junit)
    androidTestImplementation(libs.android.test.androidx.espresso.core)
}
