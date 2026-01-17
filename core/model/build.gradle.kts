plugins {
    id("blueprint.kotlin.library")
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(libs.findLibrary("kotlinx-serialization-json").get())
}
