# TODO

## 完了済み

### リネーム (feature/rename-to-blueprint)
- [x] settings.gradle.kts rootProject.name 変更
- [x] namespace/applicationId 変更 (com.mgtantheta.blueprint)
- [x] Convention Plugin ID変更 (blueprint.*)
- [x] パッケージディレクトリリネーム
- [x] Theme名変更 (BlueprintTheme)
- [x] ドキュメント更新
- [x] GitHubリポジトリ名変更

### インフラ
- [x] Detekt 導入
- [x] Spotless 導入
- [x] CI/CD (GitHub Actions)
  - [x] Build workflow (PR時)
  - [x] Lint workflow (Detekt + Spotless)
  - [x] Renovate導入

### Core モジュール
- [x] core:model (共通データモデル)
- [x] core:network (Ktor Client)
- [x] core:data (Repository実装)
- [x] core:domain (UseCase, Repository Interface)
- [x] core:designsystem (Theme, Components)

### ライブラリ導入
- [x] Hilt (DI)
- [x] Navigation 3
- [x] Ktor Client
- [x] Kotlinx Serialization
- [x] Coil 3
- [x] Lottie Compose

### Feature モジュール
- [x] feature:home

### Convention Plugins
- [x] blueprint.android.application
- [x] blueprint.android.application.compose
- [x] blueprint.android.library
- [x] blueprint.android.library.compose
- [x] blueprint.android.hilt
- [x] blueprint.android.feature
- [x] blueprint.kotlin.library

## 次のタスク

### インフラ
- [ ] Release workflow (tag push時)
- [ ] Kover (カバレッジ)

### Core モジュール
- [ ] core:common (ユーティリティ)
- [ ] core:datastore (DataStore)
- [ ] core:ui (再利用可能Component)
- [ ] core:testing (テスト共通)
- [ ] core:database (Room - 必要に応じて)

### Feature モジュール
- [ ] feature:settings

### テスト
- [ ] ViewModel テスト
- [ ] Repository テスト
- [ ] UseCase テスト
- [ ] UI テスト (Compose)

### AGP 9.0.0 対応 (feature/agp-2)
- [x] AGP 9.0.0 へアップグレード
- [x] Convention Plugins を新DSL対応に修正
- [ ] Hilt が newDsl 対応後、android.newDsl=false を削除
- [ ] android.builtInKotlin=false を削除 (組み込みKotlin使用)

## 参考
- [Now in Android](https://github.com/android/nowinandroid)
- [README.md](../README.md)
