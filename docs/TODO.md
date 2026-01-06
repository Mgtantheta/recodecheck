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

## 次のタスク

### インフラ
- [ ] CI/CD (GitHub Actions)
  - [x] Build workflow (PR時)
  - [x] Lint workflow (Detekt + Spotless)
  - [ ] Release workflow (tag push時)
  - [x] Renovate導入

### Core モジュール
- [ ] core:common (ユーティリティ)
- [ ] core:model (共通データモデル)
- [x] core:network (Ktor Client)
- [x] core:data (Repository実装)
- [ ] core:domain (UseCase)
- [ ] core:datastore (DataStore)
- [ ] core:ui (再利用可能Component)

### ライブラリ導入
- [x] Hilt (DI)
- [x] Navigation 3
- [x] Ktor Client
- [x] Kotlinx Serialization
- [x] Coil 3
- [x] Lottie Compose

### Feature モジュール
- [ ] feature:home
- [ ] feature:settings

## Convention Plugins 追加予定
- [x] blueprint.android.hilt
- [x] blueprint.android.feature
- [x] blueprint.kotlin.library

## 参考
- [Now in Android](https://github.com/android/nowinandroid)
- [README.md](../README.md)
