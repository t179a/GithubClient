# GithubClient

## メモ
- github secret
    - 使い方を調べる
- accessTokenを平文で保存しているのは良くない　　　
    - EncryptedSharedPreferencesを使う
- SharedPreferencesの場合context経由でgetできるが
    - EncrypedSharedPreferencesを複数のmoduleで使う方法を考える
- accessTokenの有効期限に対応
- 全般的にエラーハンドリングしていない点を修正
   




## UML
- 時間があったら、[mermaid](https://mermaid.js.org/)か[PlantUML](https://plantuml.com/)で書くかも

## Dependencies
- DI
    - [dagger:hilt](https://dagger.dev/hilt/)
- Navigation
    - [navigation-compose](https://developer.android.com/jetpack/compose/navigation)
- API
    - [ktor-client](https://ktor.io/)
    - [kotlinx-serialization](https://github.com/Kotlin/kotlinx.serialization)
- UI
    - [Jetpack Compose](https://developer.android.com/jetpack/compose)
- Image Loading
    - [Coil](https://coil-kt.github.io/coil/)
- Presentation Logic
    - [Molecule](https://github.com/cashapp/molecule)
- Data-Storage
    - [Room](https://developer.android.com/training/data-storage/room)
