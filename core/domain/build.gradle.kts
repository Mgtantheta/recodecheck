plugins {
    id("blueprint.kotlin.library")
}

dependencies {
    api(project(":core:model"))

    implementation(platform(libs.findLibrary("kotlinx-coroutines-bom").get()))
    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
}
