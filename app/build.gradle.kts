plugins {
    id("blueprint.android.application.compose")
    alias(libs.plugins.android.application) apply false
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
    implementation(libs.findLibrary("coil-compose").get())
    implementation(libs.findLibrary("coil-network-ktor3").get())
    implementation(libs.findLibrary("ktor-client-okhttp").get())
}
