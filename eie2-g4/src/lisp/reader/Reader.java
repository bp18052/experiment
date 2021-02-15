package lisp.reader;

import java.io.BufferedReader;
import java.io.IOException;

import lisp.eval.Bool;
import lisp.eval.ConsCell;
import lisp.eval.Environment;
import lisp.eval.Flo;
import lisp.eval.Int;
import lisp.eval.SExpression;
import lisp.eval.Symbol;
import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/**
 * 構文解析器
 * 
 * @author tetsuya
 *
 */
public class Reader {
	/**
	 * 字句解析器
	 */
	private Lexer lexer;
	private int lexer_length;
	private int len_counter;
	/**
	 * 先読みの字句
	 */
	private Token token = null;

	/**
	 * 括弧の入れ子レベル。式を読み終わった時にnestingLevelが0ならば、そこまでの式を評価する。
	 */
	private int nestingLevel = 0;

	/**
	 * car cdrのhash
	 */
	private Environment env_car;
	private Environment env_cdr;

	/**
	 * コンストラクタ
	 * 
	 * @param in 入力
	 */
	public Reader(BufferedReader in) {
		this.lexer = new Lexer(in);
	}

	/**
	 * <pre>
	 * {@literal <S式>} ::= {@literal <整数値>} | {@literal <記号>} | {@literal <真理値>} | '(' ({@literal <S式>} '.' {@literal <S式>})? ')'
	 * </pre>
	 * 
	 * @return S式
	 * @throws LispException
	 * @throws IOException
	 */
	SExpression sExpression() throws IOException, LispException {
		// 整数値
		if (this.token.getKind() == Token.Kind.NUMBER) {
			int value = this.token.getIntValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return Int.valueOf(value);
		}
		// 実数
		if (this.token.getKind() == Token.Kind.FLO) {
			float value = this.token.getFloatValue();
			if (this.nestingLevel != 0) {
				// 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return Flo.valueOf(value);
		}
		// 記号
		if (this.token.getKind() == Token.Kind.SYMBOL) {
			String value = this.token.getSymbolValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return Symbol.getInstance(value);
		}
		// 真理値
		if (this.token.getKind() == Token.Kind.BOOLEAN) {
			boolean value = this.token.getBooleanValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
			}
			return Bool.valueOf(value);
		}
		// 文字列
		if (this.token.getKind() == Token.Kind.STRING) {
			String value = this.token.getSymbolValue();
			if (this.nestingLevel != 0) {
				this.token = this.lexer.getNextToken();
			}
			return Symbol.getInstance(value);
		}
		// ' クォート
		if (this.token.getKind() == Token.Kind.QUOTE) {
			this.token = this.lexer.getNextToken();
			ConsCell.ListBuilder listBuilder = ConsCell.builder();
			listBuilder.tail(Symbol.getInstance("quote"));
			listBuilder.tail(sExpression());
			return listBuilder.build();
		}
		// '(' ')' or '(' <S式> . <S式> ')'
		if (this.token.getKind() == Token.Kind.LPAREN) {
			this.nestingLevel++;
			this.token = this.lexer.getNextToken();

			ConsCell.ListBuilder listBuilder = ConsCell.builder();
			while (true) {
				if (this.token.getKind() == Token.Kind.RPAREN) {
					this.nestingLevel--;
					if (this.nestingLevel != 0) { // 式が未完成
						this.token = this.lexer.getNextToken();
					}
					return listBuilder.build();
				}
				// car
				SExpression car = sExpression();
				// '.'
				if (this.token.getKind() == Token.Kind.DOT) {
					this.token = this.lexer.getNextToken();
					// cdr
					SExpression cdr = sExpression();
					if (this.token.getKind() != Token.Kind.RPAREN) {
						throw new SyntaxErrorException("')' expected");
					}
					if (this.lexer_length != 0) { // 式が未完成
						this.token = this.lexer.getNextToken();
					}
					return ConsCell.getInstance(car, cdr);
				}
				listBuilder.tail(car);
			}
		}
		throw new SyntaxErrorException("Invalid expression:" + this.token.getKind());
	}

	public SExpression read() throws IOException, LispException {
		this.nestingLevel = 0;
		this.token = this.lexer.getNextToken();
		return sExpression();
	}
}