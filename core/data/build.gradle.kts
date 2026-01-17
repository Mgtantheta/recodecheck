plugins {
    id("blueprint.android.library")
    id("blueprint.android.hilt")
}

android {
    namespace = "com.mgtantheta.blueprint.core.data"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))

    implementation(platform(libs.findLibrary("kotlinx-coroutines-bom").get()))
    implementation(libs.findLibrary("kotlinx-coroutines-android").get())
}
