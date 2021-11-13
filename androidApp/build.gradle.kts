plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    val coroutinesVersion = "1.5.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Integration with activities
    implementation("androidx.activity:activity-compose:1.4.0")
    // Compose Material Design
    implementation("androidx.compose.material:material:1.0.5")
    // Animations
    implementation("androidx.compose.animation:animation:1.0.5")
    // Tooling support (Previews, etc.)
    implementation("androidx.compose.ui:ui-tooling:1.0.5")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")

    // When using a MDC theme
    implementation("com.google.android.material:compose-theme-adapter:1.0.4")
    // When using a AppCompat theme
    implementation("com.google.accompanist:accompanist-appcompat-theme:0.16.0")

    implementation("io.coil-kt:coil-compose:1.4.0")

    val pagingVersion = "3.0.1"

    implementation("androidx.paging:paging-runtime:$pagingVersion")
    implementation("androidx.paging:paging-compose:1.0.0-alpha14")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "io.vnicius.github.kmmtest.android"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.1.0-beta02"
    }
}
