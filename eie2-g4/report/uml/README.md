# UML

## 概要
- このディレクトリにはPlantUML( https://plantuml.com/ja/ )で記述したUMLを配置する.

## PlantUMLファイル(*.pu)
### サンプル
- env_define.pu
	- 環境へのdefineのシーケンス図 (未完成)
- env_get_value_of.pu
	- 環境からの値取得のシーケンス図 (未完成)
- env_set.pu
	- 環境へのset!のシーケンス図 (未完成)
- repl.pu
	- REPLのシーケンス図 (未完成)

## 表示方法
### Eclipse 
- 図の表示方法
	- Window -> Show View -> Other -> PlantUml -> PlantUML
- 表示している図のPlantUML記述の表示方法
	- Window -> Show View -> Other -> PlantUml -> PlantUML Source

### コマンドライン
plantumlコマンドにpuファイルを与えると, そのpuファイルがあるディレクトリにpngファイルが生成される.
~~~
$ plantuml sample_repl.pu
~~~
この例の場合, カレントディレクトリにsample_repl.pngが出力される.

ローカルのGitリポジトリ内で上記コマンドを実行するとpngファイルがリポジトリ内に生成されるので, 
誤ってpngファイルがリポジトリに登録されてしまう可能性がある.

そこでpngファイルを生成する場合はデスクトップで操作すると良いだろう.
~~~
$ cp sample_repl.pu ~/Desktop/
$ plantuml ~/Desktop/sample_repl.pu
~~~
この例ではデスクトップにpngファイルが生成される.

### Firefox / Chrome
- 拡張機能 PlantUML Visualizer を追加することで, PlantUML記述を図に変換して表示できる.
	- 情報実験II用に配布した仮想環境のFirefoxにはこの拡張機能が追加されている.
	- GitHubのテキスト領域内でもこの機能は働く. 例えば下記の記述があるとシーケンス図が描画される. 
<pre>
~~~
@startuml
Alice -> Bob
@enduml
~~~
</pre> 
- FireFox用
	- https://addons.mozilla.org/ja/firefox/addon/plantuml-visualizer/
- Chrome用
	- https://chrome.google.com/webstore/detail/plantuml-visualizer/ffaloebcmkogfdkemcekamlmfkkmgkcf?hl=ja-JP
 
### Google Docs / Google Sheets
- PlantUML Gizmo というAdd-onを導入することで, PlantUMLが利用できるようになる.
	- https://gsuite.google.com/marketplace/app/plantuml_gizmo/950520042571
