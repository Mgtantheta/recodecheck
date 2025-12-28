# Hilt + Navigation 3 導入 TODO

## 概要
- Hilt: DI基盤
- Navigation 3: NavDisplay + NavigationState + Navigator
- CLAUDE.md: NiAスタイルに更新

---

## 変更ファイル一覧

| # | ファイル | 内容 |
|---|----------|------|
| 1 | `gradle/libs.versions.toml` | Hilt, Nav3, KSP追加 |
| 2 | `build.gradle.kts` (root) | プラグイン宣言 |
| 3 | `build-logic/convention/build.gradle.kts` | gradlePlugin追加 |
| 4 | `build-logic/.../AndroidHiltConventionPlugin.kt` | 新規 |
| 5 | `app/build.gradle.kts` | プラグイン適用 |
| 6 | `app/.../BlueprintApplication.kt` | 新規 (@HiltAndroidApp) |
| 7 | `app/AndroidManifest.xml` | Application指定 |
| 8 | `app/.../navigation/Routes.kt` | 新規 |
| 9 | `app/.../navigation/NavigationState.kt` | 新規 |
| 10 | `app/.../navigation/Navigator.kt` | 新規 |
| 11 | `app/.../navigation/BlueprintNavigation.kt` | 新規 |
| 12 | `app/.../ui/HomeScreen.kt` | 新規 |
| 13 | `app/.../MainActivity.kt` | 更新 |
| 14 | `CLAUDE.md` | NiAスタイル更新 |

---

## 依存関係

```toml
# versions
hilt = "2.54"
ksp = "2.0.21-1.0.28"
nav3 = "1.0.0"
lifecycleViewmodelNav3 = "2.9.0"
kotlinxSerializationJson = "1.7.3"

# libraries
hilt-android
hilt-compiler
navigation3-runtime
navigation3-ui
lifecycle-viewmodel-navigation3
kotlinx-serialization-json
hilt-gradlePlugin
ksp-gradlePlugin

# plugins
hilt
ksp
kotlin-serialization
```

---

## 実装順序

1. libs.versions.toml 更新
2. build.gradle.kts (root) 更新
3. build-logic 更新 + AndroidHiltConventionPlugin作成
4. app/build.gradle.kts 更新
5. BlueprintApplication.kt 作成
6. AndroidManifest.xml 更新
7. navigation/ ディレクトリ作成
   - Routes.kt
   - NavigationState.kt
   - Navigator.kt
   - BlueprintNavigation.kt
8. ui/HomeScreen.kt 作成
9. MainActivity.kt 更新
10. CLAUDE.md 更新
11. ビルド確認

---

## Navigation 3 構成

```
NavigationState.kt  - 状態保持
Navigator.kt        - イベント処理 (navigate, goBack)
LocalNavigator      - CompositionLocal
NavDisplay          - UI表示
entryProvider       - 画面定義
```

---

## 参考

- [Navigation 3 公式ドキュメント](https://developer.android.com/guide/navigation/navigation-3)
- [Migration Guide](https://developer.android.com/guide/navigation/migration/nav2-to-nav3)
- [nav3-recipes](https://github.com/android/nav3-recipes)
