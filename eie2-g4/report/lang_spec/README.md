# Lisp言語の仕様

## 文法
- S式の字句
	- SexpLexer.g4
- S式の文法
	- SexpParser.g4
- Eclipseでの構文図式の表示方法
	- Window -> Show View -> Other -> ANTLR 4 -> Syntax Diagram

## 拡張

- グラフィックを表示するウィンドウが表示されるタイミング
    - コマンドを入力すると同時に表示される //ex) gosh > kochsnow
- ウィンドウの大きさ
　　- ４００×５００
- 座標の取り方
　　- 左上に原点を配置

### 導入するデータ型

導入するデータ型あればここに記述する.

### 導入する手続き

- 導入する手続きの記述例を示す.

#### 手続き graphics-draw-line
- 書式
	- (graphics-draw-line x1 y1 x2 y2)
- 意味 
	- 座標(x1, y1)から座標(x2, y2)を結ぶ線分を描画する. 未定義値を返す.
	
#### 手続き graphics-clear
- 書式
	-  (graphics-clear)
- 意味
	-  描画内容を消去する. 未定義値を返す.

#### 手続き graphics-set-color
- 書式
	-  (graphics-set-color c)
- 意味
	-  色(c)を指定する. ex) redの場合は予めturtle.scmにて(graphics-set-color red)の文を追加しておく
	   未定義値を返す.

#### 手続き graphics-fill-oval
- 書式
	-  (graphics-fill-oval)
- 意味
	-  現在の色で指定された矩形の中の楕円を塗りつぶす. 未定義値を返す.

#### 画面　screen
- 書式
	- (screen)
- 内容
	- 画面サイズは400:500に指定
	- コッホ雪片を描画する背景(ボード)の色は白
