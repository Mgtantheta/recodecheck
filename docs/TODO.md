# TODO

## 現在のブランチ (feature/add-design-system)
- [x] build-logic 作成
- [x] Convention Plugins (4種)
- [x] core:designsystem 作成
- [x] 依存関係をConvention Plugin内で管理
- [ ] ビルド確認 → mainにマージ

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
- [ ] recodecheck.android.hilt
- [ ] recodecheck.android.feature
- [ ] recodecheck.kotlin.library

## 参考
- [Now in Android](https://github.com/android/nowinandroid)
- [README.md](../README.md)
