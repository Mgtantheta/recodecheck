plugins {
    id("blueprint.android.application.compose")
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
}
