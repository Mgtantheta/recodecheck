# Feature Module 作成ガイド

Navigation 3 を使った feature モジュールの作成手順。

## 目標構成

```
feature/home/
├── build.gradle.kts
└── src/main/java/com/mgtantheta/blueprint/feature/home/
    ├── HomeRoute.kt       # Route定義 + Navigation拡張関数
    └── HomeScreen.kt      # UI
```

---

## Step 1: ディレクトリ作成

```bash
mkdir -p feature/home/src/main/java/com/mgtantheta/blueprint/feature/home
```

---

## Step 2: settings.gradle.kts に追加

```kotlin
include(":feature:home")
```

---

## Step 3: feature/home/build.gradle.kts 作成

```kotlin
plugins {
    id("blueprint.android.library.compose")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mgtantheta.blueprint.feature.home"
}

dependencies {
    // Navigation 3
    implementation(libs.androidx.navigation3.runtime)

    // Design System
    implementation(project(":core:designsystem"))
}
```

> **Note**: `blueprint.android.library.compose` プラグインが必要。
> なければ `build-logic` に作成するか、直接記述する。

---

## Step 4: HomeRoute.kt 作成

`feature/home/src/main/java/com/mgtantheta/blueprint/feature/home/HomeRoute.kt`

```kotlin
package com.mgtantheta.blueprint.feature.home

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute : NavKey

/**
 * Navigation 3 の entryProvider で使う拡張関数
 *
 * 使用例:
 * ```
 * entryProvider {
 *     homeScreen()
 * }
 * ```
 */
fun EntryProviderScope<NavKey>.homeScreen() {
    entry<HomeRoute> { HomeScreen() }
}
```

---

## Step 5: HomeScreen.kt 移動

`app/src/main/java/.../ui/HomeScreen.kt` を
`feature/home/src/main/java/.../feature/home/HomeScreen.kt` に移動

```kotlin
package com.mgtantheta.blueprint.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = "Hello Android!")
        }
    }
}
```

---

## Step 6: app/build.gradle.kts 更新

```kotlin
dependencies {
    // Feature modules
    implementation(project(":feature:home"))

    // ... 他の依存関係
}
```

---

## Step 7: app モジュールの修正

### BlueprintNavigation.kt

```kotlin
package com.mgtantheta.blueprint.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mgtantheta.blueprint.feature.home.HomeRoute
import com.mgtantheta.blueprint.feature.home.homeScreen  // 拡張関数

@Composable
fun BlueprintNavigation(
    modifier: Modifier = Modifier,
) {
    val navigationState = rememberNavigationState(
        startRoute = HomeRoute,
        topLevelRoutes = setOf(HomeRoute),
    )
    val navigator = remember { Navigator(navigationState) }

    val provider: (NavKey) -> NavEntry<NavKey> = entryProvider {
        homeScreen()  // 拡張関数を呼び出し
    }

    CompositionLocalProvider(LocalNavigator provides navigator) {
        NavDisplay(
            entries = navigationState.toEntries(provider),
            onBack = navigator::goBack,
            modifier = modifier,
        )
    }
}
```

### 削除するファイル

- `app/.../navigation/HomeRoute.kt` (feature:home に移動済み)
- `app/.../ui/HomeScreen.kt` (feature:home に移動済み)

---

## Step 8: ビルド確認

```bash
./gradlew :feature:home:assembleDebug
./gradlew assembleDebug
```

---

## Convention Plugin が無い場合

`blueprint.android.library.compose` が無い場合、直接記述:

```kotlin
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.mgtantheta.blueprint.feature.home"
    compileSdk = 36

    defaultConfig {
        minSdk = 28
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)

    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.kotlinx.serialization.json)

    implementation(project(":core:designsystem"))
}
```

---

## 参考リンク

- [Modularize navigation code - Android Developers](https://developer.android.com/guide/navigation/navigation-3/modularize)
- [EntryProviderScope API](https://developer.android.com/reference/kotlin/androidx/navigation3/runtime/EntryProviderBuilder)