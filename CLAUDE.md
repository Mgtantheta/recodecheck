# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build the project
./gradlew build

# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Clean build
./gradlew clean

# Run unit tests
./gradlew test

# Run a single unit test class
./gradlew test --tests "com.example.recodecheck.ExampleUnitTest"

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Install debug APK on connected device
./gradlew installDebug
```

## Architecture

This is a single-module Android application using Jetpack Compose for UI:

- **Language**: Kotlin with JVM target 11
- **UI Framework**: Jetpack Compose with Material 3
- **Min SDK**: 28 (Android 9)
- **Target SDK**: 36

### Project Structure

- `app/src/main/java/com/example/recodecheck/` - Main application code
  - `MainActivity.kt` - Entry point, hosts Compose UI
  - `ui/theme/` - Material 3 theming (colors, typography, theme configuration)
- `app/src/test/` - Unit tests (JUnit 4)
- `app/src/androidTest/` - Instrumented tests (AndroidJUnit, Espresso)

### Dependencies

Dependencies are managed via version catalog in `gradle/libs.versions.toml`. Key dependencies:
- Compose BOM for coordinated Compose versions
- AndroidX Core KTX, Lifecycle, Activity Compose
