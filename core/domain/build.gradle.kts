plugins {
    id("blueprint.kotlin.library")
}

dependencies {
    api(project(":core:model"))

    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
}
