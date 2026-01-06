plugins {
    id("blueprint.android.library")
    id("blueprint.android.hilt")
}

android {
    namespace = "com.mgtantheta.blueprint.core.data"
}

dependencies {
    implementation(project(":core:network"))

    implementation(libs.findLibrary("kotlinx-coroutines-android").get())
}
