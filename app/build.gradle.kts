plugins {
    id("recodecheck.android.application.compose")
}

android {
    namespace = "com.example.recodecheck"

    defaultConfig {
        applicationId = "com.example.recodecheck"
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
}

dependencies {
    implementation(project(":core:designsystem"))
}
