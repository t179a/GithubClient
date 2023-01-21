# GithubClient

## メモ
- Github OAuthを使ってgithubのaccess tokenが欲しい
- callback URLは下のどっちが優先されるのか
    - Github/Settings/Developer settings/OAuth App
    - Android app内でparameterとして渡すやつ

- 定数Client_id, Client_secretをどこに置けば良いのか  
-> github secretっぽい

- github secret
    - 使い方を調べる
   
- アプリ -> ブラウザで認証 -> アプリに戻った際に、account画面に戻りたい(今は検索画面に戻ってしまっている)  
-> composableのdeeplinkで解決

- OAuthの　state parameter(推測不能なランダムの文字列。 クロスサイトリクエストフォージェリ攻撃に対する保護として使われる。)をandroid app側でどう作るか  
-> JavaのUUIDらしい

- UIの状態
    - ログイン -> CirclarProgressIndicator(callbackの処理) -> ログイン済
- ログイン
    - UIでIntent、parameter(client_id,redirect_uri)
    - UIをCirclerProgressIndicatorに変更
    
- callbackの処理
    - UIでIntentから"code"を取得
    - "code"をviewModelに渡す
    - stateを作成
    - state,"code",client_id,client_secretを使ってを使って、POST
    - 
- 2回目のcallbackの処理
    - Intentからaccess_tokenを取得
    - UIを成功画面に変更
    




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
