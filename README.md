# Blueprint

モダンなAndroidアーキテクチャを実践するためのサンプルプロジェクト。
[Now in Android](https://github.com/android/nowinandroid)を参考に、最新のライブラリとベストプラクティスを採用。

## Tech Stack

| カテゴリ | 技術 |
|----------|------|
| **Language** | Kotlin 2.2+ |
| **UI** | Jetpack Compose + Material 3 |
| **Navigation** | Navigation 3 (型安全) |
| **DI** | Hilt |
| **Networking** | Ktor Client |
| **Serialization** | Kotlinx Serialization |
| **Image Loading** | Coil 3 |
| **Local Storage** | DataStore (+ Room 検討中) |
| **Async** | Coroutines + Flow |
| **Build** | Gradle 9.3 + Kotlin DSL + Convention Plugins |
| **CI/CD** | GitHub Actions |
| **Code Quality** | Detekt + Spotless |

## Architecture

```
MVVM + Repository + DDD (Clean Architecture)
```

```
┌─────────────────────────────────────────────────────────┐
│                      Presentation                        │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐      │
│  │   Screen    │  │  ViewModel  │  │   UiState   │      │
│  └─────────────┘  └─────────────┘  └─────────────┘      │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────┐
│                        Domain                            │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐      │
│  │   UseCase   │  │   Entity    │  │  Repository │      │
│  │             │  │   (Model)   │  │ (Interface) │      │
│  └─────────────┘  └─────────────┘  └─────────────┘      │
└─────────────────────────────────────────────────────────┘
                          │
                          ▼
┌─────────────────────────────────────────────────────────┐
│                         Data                             │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐      │
│  │ Repository  │  │ DataSource  │  │    Model    │      │
│  │   (Impl)    │  │(Remote/Local)│  │  (DTO/Entity)│    │
│  └─────────────┘  └─────────────┘  └─────────────┘      │
└─────────────────────────────────────────────────────────┘
```

## Module Structure

Now in Android を参考にしたマルチモジュール構成:

```
blueprint/
├── app/                          # Application module
│   └── src/
├── core/                         # Core modules
│   ├── common/                   # 共通ユーティリティ (予定)
│   ├── data/                     # Repository実装
│   ├── database/                 # Room (検討中)
│   ├── datastore/                # DataStore (予定)
│   ├── designsystem/             # Design tokens, Theme, Components
│   ├── domain/                   # UseCase, Domain models
│   ├── model/                    # 共通モデル
│   ├── network/                  # Ktor Client
│   ├── testing/                  # テスト共通 (予定)
│   └── ui/                       # 共通UI Components (予定)
├── feature/                      # Feature modules
│   ├── home/
│   └── settings/ (予定)
├── build-logic/                  # Convention Plugins
│   └── convention/
└── gradle/
    └── libs.versions.toml        # Version Catalog
```

### Module Dependencies

```
app
 └── feature:*
      └── core:ui
           └── core:designsystem
      └── core:domain
           └── core:model
      └── core:data
           └── core:network
           └── core:datastore
           └── core:database (検討中)
```

## Build Logic (Convention Plugins)

`build-logic/`で共通ビルド設定を管理:

| Plugin | 用途 |
|--------|------|
| `blueprint.android.application` | Applicationモジュール用 |
| `blueprint.android.application.compose` | Application + Compose |
| `blueprint.android.library` | Libraryモジュール用 |
| `blueprint.android.library.compose` | Library + Compose |
| `blueprint.android.hilt` | Hilt設定 |
| `blueprint.android.feature` | Featureモジュール用 |
| `blueprint.kotlin.library` | Pure Kotlinモジュール用 |

## CI/CD (GitHub Actions)

### Workflows

| Workflow | トリガー | 内容 |
|----------|----------|------|
| **Build** | Push, PR | ビルド + ユニットテスト |
| **Lint** | PR | Detekt + Spotless チェック |
| **UI Test** | PR (main) | Instrumented tests |
| **Release** | Tag push | APK/AAB ビルド + Release作成 (予定) |

### Pipeline

```
PR作成
  │
  ├── Spotless Check (フォーマット)
  ├── Detekt (静的解析)
  ├── Unit Tests
  └── Build Debug APK
         │
         ▼
    レビュー & マージ
         │
         ▼
    main ブランチ
         │
         ├── UI Tests (Emulator)
         └── Build Release
```

## Code Quality

### Detekt

静的解析ツール。以下をチェック:
- コード複雑度 (Cyclomatic Complexity)
- 命名規則
- 潜在的バグ
- コードスメル

```bash
./gradlew detekt
```

### Spotless

コードフォーマッター:
- ktlint ルール適用
- インポート順序統一
- 末尾改行

```bash
# チェック
./gradlew spotlessCheck

# 自動修正
./gradlew spotlessApply
```

## Testing Strategy

### Test Pyramid

```
        ╱╲
       ╱  ╲        UI Tests (10%)
      ╱────╲       Espresso, Compose UI Test
     ╱      ╲
    ╱────────╲     Integration Tests (20%)
   ╱          ╲    Repository, UseCase
  ╱────────────╲
 ╱              ╲  Unit Tests (70%)
╱────────────────╲ ViewModel, Mapper, Util
```

### Testing Libraries

| 種別 | ライブラリ |
|------|-----------|
| **Unit Test** | JUnit5, Turbine (Flow), MockK |
| **UI Test** | Compose UI Test, Espresso |
| **Assertion** | Truth, Kotest Assertions |
| **Fake/Mock** | Hilt Testing, MockK |

### Coverage Goals

| モジュール | 目標 |
|-----------|------|
| `core:domain` | 90%+ |
| `core:data` | 80%+ |
| `feature:*` | 70%+ |

### Test Commands

```bash
# 全ユニットテスト
./gradlew test

# カバレッジレポート (Kover導入後)
./gradlew koverHtmlReport

# UIテスト (要エミュレータ)
./gradlew connectedAndroidTest
```

## Getting Started

### Requirements

- Android Studio Meerkat+ (2025.1.1)
- JDK 21
- Android SDK 36 (compileSdk)

### Setup

```bash
# Clone
git clone https://github.com/Mgtantheta/blueprint.git
cd blueprint

# Build
./gradlew assembleDebug

# Run tests
./gradlew test
```

## Roadmap

- [x] マルチモジュール化
- [x] Convention Plugins 導入
- [x] Hilt 導入
- [x] Navigation 3 導入
- [x] Ktor Client 導入
- [x] Detekt + Spotless 導入
- [x] GitHub Actions CI/CD
- [x] Feature: Home
- [ ] Feature: Settings
- [ ] Kover (カバレッジ)
- [ ] AGP 9.0.0 対応

## References

- [Now in Android](https://github.com/android/nowinandroid) - アーキテクチャ参考
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Navigation 3](https://developer.android.com/guide/navigation/design/type-safety)
- [Hilt](https://dagger.dev/hilt/)
- [Ktor Client](https://ktor.io/docs/client-create-new-application.html)

## License

```
MIT License
```
