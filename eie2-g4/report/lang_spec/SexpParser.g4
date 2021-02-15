// S式(S-expression)の文法（字句の並び方に関する規則）
parser grammar SexpParser;

options { tokenVocab=SexpLexer; } // 字句定義

/**
 * 次の書き方で、左辺の<記号>とは右辺の<記号の並び方の規則> であることを定義する。
 *　 <記号> : <記号の並び方の規則> ;
 *  
 * 右辺の<記号の並び方の規則>の書き方
 *  　　　 記号 　　　　　　    意味
 * 　　　  ' ' 　　　　 引用符で囲まれた文字列
 *   　　　[ ]　　　　　 集合
 *   　　　( )　　　　　 カッコ内をグループ化
 *    　　　|　　　　　  「または」の意味
 *    　　　? 　　　　　 直前のものが0回または1回出現
 *    　　　+　　　　　  直前のものが1回以上出現
 *    　　　*   　　　　直前のものが0回以上出現
 *   <規則1> <規則2>　　<規則1>を満たす記号列の後に<規則2>を満たす記号列が続くという規則
 */ 

sexp: INT
    | SYMBOL
    | BOOLEAN
    | FLO
    | QUOTE(sexp)
    | LPAREN (sexp (DOT? sexp?))? RPAREN
    | SIN INT+
    | COS INT+
    ;
