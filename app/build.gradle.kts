plugins {
    id("blueprint.android.application.compose")
    id("blueprint.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mgtantheta.blueprint"

    defaultConfig {
        applicationId = "com.mgtantheta.blueprint"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":feature:home"))

    // Navigation 3 (TODO: Version Catalog accessor 対応)
    implementation("androidx.navigation3:navigation3-runtime:1.0.0")
    implementation("androidx.navigation3:navigation3-ui:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-navigation3:2.10.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
}
