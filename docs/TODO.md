# TODO

## 現在のブランチ (feature/rename-to-blueprint)
- [x] settings.gradle.kts rootProject.name 変更
- [x] namespace/applicationId 変更 (com.mgtantheta.blueprint)
- [x] Convention Plugin ID変更 (blueprint.*)
- [ ] パッケージディレクトリリネーム
- [ ] Theme名変更 (BlueprintTheme)
- [ ] ドキュメント更新
- [ ] GitHubリポジトリ名変更

## 次のタスク (ブランチ分けて実施)

### インフラ
- [ ] CI/CD (GitHub Actions)
  - [ ] Build workflow (PR時)
  - [ ] Lint workflow (Detekt + Spotless)
  - [ ] Release workflow (tag push時)
  - [ ] Renovate導入
- [ ] Detekt 導入
- [ ] Spotless 導入

### Core モジュール
- [ ] core:common (ユーティリティ)
- [ ] core:model (共通データモデル)
- [ ] core:network (Ktor Client)
- [ ] core:data (Repository実装)
- [ ] core:domain (UseCase)
- [ ] core:datastore (DataStore)
- [ ] core:ui (再利用可能Component)

### ライブラリ導入
- [ ] Hilt (DI)
- [ ] Navigation 3
- [ ] Ktor Client
- [ ] Kotlinx Serialization
- [ ] Coil
- [ ] Lottie Compose

### Feature モジュール
- [ ] feature:home
- [ ] feature:settings

## Convention Plugins 追加予定
- [ ] blueprint.android.hilt
- [ ] blueprint.android.feature
- [ ] blueprint.kotlin.library

## 参考
- [Now in Android](https://github.com/android/nowinandroid)
- [README.md](../README.md)
