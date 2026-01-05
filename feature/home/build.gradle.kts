plugins {
    id("blueprint.android.feature")
}

android {
    namespace = "com.mgtantheta.feature.home"
}

dependencies {
    implementation(libs.findLibrary("coil-compose").get())
    implementation(libs.findLibrary("coil-network-ktor3").get())
}
