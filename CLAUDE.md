# CLAUDE.md

Claude Code (claude.ai/code) 向けのプロジェクトガイド。

## Build Commands

```bash
# ビルド
./gradlew build                    # 全体ビルド
./gradlew assembleDebug            # Debug APK
./gradlew assembleRelease          # Release APK
./gradlew clean                    # クリーン

# テスト
./gradlew test                     # 全ユニットテスト
./gradlew :app:test                # appモジュールのみ
./gradlew :feature:home:test       # 特定モジュール
./gradlew test --tests "*.FooTest" # 特定テストクラス
./gradlew connectedAndroidTest     # UIテスト (要エミュレータ)

# コード品質
./gradlew detekt                   # 静的解析
./gradlew spotlessCheck            # フォーマットチェック
./gradlew spotlessApply            # フォーマット自動修正

# インストール
./gradlew installDebug             # Debug APKインストール

# 依存関係
./gradlew :app:dependencies        # 依存関係ツリー
```

## Architecture

MVVM + Repository + DDD (Clean Architecture)

```
Presentation (UI)
    │ UiState, UiEvent
    ▼
 ViewModel
    │ suspend fun, Flow
    ▼
Domain (UseCase)
    │ Entity
    ▼
Repository (Interface)
    │
    ▼
Data (Implementation)
    ├── Remote (Ktor)
    └── Local (DataStore / Room)
```

## Module Structure (Target)

```
blueprint/
├── app/                      # Application module (エントリーポイント)
├── core/
│   ├── common/               # 共通ユーティリティ、拡張関数
│   ├── data/                 # Repository実装
│   ├── datastore/            # DataStore (Preferences)
│   ├── database/             # Room (必要に応じて)
│   ├── designsystem/         # Theme, Color, Typography, 共通Component
│   ├── domain/               # UseCase, Domain Model, Repository Interface
│   ├── model/                # 共通データモデル
│   ├── network/              # Ktor Client, API定義
│   ├── testing/              # テスト共通 (Fake, TestRule)
│   └── ui/                   # 再利用可能なUI Component
├── feature/
│   ├── home/                 # ホーム機能
│   ├── settings/             # 設定機能
│   └── .../                  # 追加機能
├── build-logic/
│   └── convention/           # Convention Plugins
└── gradle/
    └── libs.versions.toml    # Version Catalog
```

## Tech Stack

| カテゴリ | 技術 |
|----------|------|
| Language | Kotlin 2.0+, JVM 17 |
| UI | Jetpack Compose, Material 3 |
| Navigation | Navigation 3 |
| DI | Hilt |
| Network | Ktor Client |
| Serialization | Kotlinx Serialization |
| Image | Coil |
| Storage | DataStore (+ Room) |
| Async | Coroutines, Flow |
| Test | JUnit5, MockK, Turbine, Truth |
| Lint | Detekt, Spotless |

## Coding Conventions

### Package Naming
```
com.mgtantheta.blueprint.feature.<feature_name>
com.mgtantheta.blueprint.core.<core_module>
```

### File Naming
- ViewModel: `<Feature>ViewModel.kt`
- Screen: `<Feature>Screen.kt`
- UseCase: `<Action><Entity>UseCase.kt` (例: `GetUserUseCase.kt`)
- Repository: `<Entity>Repository.kt`

### Compose
- Screen composable: `@Composable fun <Feature>Screen()`
- State holder: `<Feature>UiState`
- Event: `<Feature>UiEvent`

### Coroutines
- ViewModel: `viewModelScope`
- Repository: `withContext(Dispatchers.IO)`
- UseCase: suspend fun

## Key Files

| ファイル | 説明 |
|----------|------|
| `gradle/libs.versions.toml` | 依存関係バージョン管理 |
| `build-logic/convention/` | 共通ビルド設定 |
| `app/build.gradle.kts` | アプリモジュール設定 |
| `core/designsystem/` | テーマ・デザイントークン |

## Testing

```bash
# ユニットテスト
./gradlew test

# カバレッジ (Kover導入後)
./gradlew koverHtmlReport

# UIテスト
./gradlew connectedAndroidTest
```

### Test Location
- Unit: `<module>/src/test/`
- Android: `<module>/src/androidTest/`

## CI/CD

GitHub Actionsで以下を自動実行:
- PR: Lint (Detekt, Spotless) + Unit Test + Build
- main: UI Test + Release Build
- Tag: APK/AAB作成 + GitHub Release
