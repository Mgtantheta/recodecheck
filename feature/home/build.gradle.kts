plugins {
    id("blueprint.android.feature")
}

android {
    namespace = "com.mgtantheta.feature.home"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))

    implementation(libs.findLibrary("coil-compose").get())
    implementation(libs.findLibrary("coil-network-ktor3").get())
}
