import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "blueprint.android.application")
            apply(plugin = "org.jetbrains.kotlin.plugin.compose")
            apply(plugin = "blueprint.android.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            extensions.configure<ApplicationExtension> {
                configureAndroidCompose(this)
            }

            dependencies {
                // Core Android
                add("implementation", libs.findLibrary("androidx-core-ktx").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime-ktx").get())
                add("implementation", libs.findLibrary("androidx-activity-compose").get())

                // Navigation 3
                add("implementation", libs.findLibrary("androidx-navigation3-runtime").get())
                add("implementation", libs.findLibrary("androidx-navigation3-ui").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-navigation3").get())
                add("implementation", libs.findLibrary("kotlinx-serialization-json").get())

                // Testing
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx-espresso-core").get())
                add("androidTestImplementation", platform(libs.findLibrary("androidx-compose-bom").get()))
                add("androidTestImplementation", libs.findLibrary("androidx-compose-ui-test-junit4").get())
            }
        }
    }
}
