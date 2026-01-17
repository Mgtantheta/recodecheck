plugins {
    id("blueprint.android.library")
    id("blueprint.android.hilt")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mgtantheta.blueprint.core.network"
}

dependencies {
    api(project(":core:model"))

    implementation(libs.findLibrary("ktor-client-core").get())
    implementation(libs.findLibrary("ktor-client-okhttp").get())
    implementation(libs.findLibrary("ktor-client-content-negotiation").get())
    implementation(libs.findLibrary("ktor-client-logging").get())
    implementation(libs.findLibrary("ktor-serialization-kotlinx-json").get())
    implementation(libs.findLibrary("kotlinx-serialization-json").get())
}
