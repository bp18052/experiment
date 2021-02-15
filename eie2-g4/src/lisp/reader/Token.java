package lisp.reader;

/**
 * 字句
 * 
 * @author tetsuya
 *
 */
public class Token {
	/**
	 * 字句の種類
	 * 
	 * @author tetsuya
	 *
	 */
	public enum Kind {
		NUMBER, // 数値
		FLO, // 浮動小数点数
		BOOLEAN, // 真理値
		SYMBOL, // 記号
		LPAREN, // 左括弧
		RPAREN, // 右括弧
		DOT, // ドット
		SIN, // sin組み込み
		COS, // sin組み込み
		STRING, QUOTE
	}

	private Kind kind;
	private int intValue;
	private float floatValue;
	private boolean booleanValue;
	private String symbol;
	private String stringValue;

	/**
	 * 字句の種類を得る。
	 * 
	 * @return 字句の種類
	 */
	Kind getKind() {
		return this.kind;
	}

	/**
	 * 整数値を得る。
	 * 
	 * @return 整数値
	 */
	int getIntValue() {
		return this.intValue;
	}

	/**
	 * 浮動小数点数を得る。
	 * 
	 * @return 浮動小数点数
	 */
	float getFloatValue() {
		return this.floatValue;
	}

	/**
	 * 真理値を得る。
	 * 
	 * @return 真理値
	 */
	boolean getBooleanValue() {
		return this.booleanValue;
	}

	/**
	 * 記号名を得る。
	 * 
	 * @return 記号名
	 */
	String getSymbolValue() {
		return this.symbol;
	}

	/**
	 * 文字列を得る。
	 * 
	 * @return 文字列
	 */
	String getStringValue() {
		return this.stringValue;
	}

	/**
	 * 種類だけを指定して字句を構築する。
	 * 
	 * @param kind 字句の種類
	 */
	Token(Kind kind) {
		this.kind = kind;
	}

	/**
	 * 整数の字句
	 * 
	 * @param value 整数値
	 */
	Token(int value) {
		this.kind = Kind.NUMBER;
		this.intValue = value;
	}

	/**
	 * 浮動小数点数の字句
	 * 
	 * @param value 浮動小数点数
	 */
	Token(float value) {
		this.kind = Kind.FLO;
		this.floatValue = value;
	}

	/**
	 * 真理値の字句
	 * 
	 * @param value 真理値
	 */
	Token(boolean value) {
		this.kind = Kind.BOOLEAN;
		this.booleanValue = value;
	}

	/**
	 * 記号の字句
	 * 
	 * @param value 記号名
	 */
	Token(String value) {
		this.kind = Kind.SYMBOL;
		this.symbol = value;
	}

	/**
	 * 文字列の字句
	 * 
	 * @param value  文字列
	 * @param string
	 */
	Token(String value, int string) {
		this.kind = Kind.STRING;
		this.stringValue = value;
	}

	@Override
	public String toString() {
		// 数値
		if (this.kind == Kind.NUMBER) {
			return "Token (Number, " + this.intValue + ")";
		}
		// 浮動小数点数
		if (this.kind == Kind.FLO) {
			return "Token (Float, " + this.floatValue + ")";
		}
		// 真理値
		if (this.kind == Kind.BOOLEAN) {
			return "Token (Boolean, " + this.booleanValue + ")";
		}
		// 記号
		if (this.kind == Kind.SYMBOL) {
			return "Token (Symbol, " + this.symbol + ")";
		}
		// 左括弧
		if (this.kind == Kind.LPAREN) {
			return "Token (LeftPar)";
		}
		// 右括弧
		if (this.kind == Kind.RPAREN) {
			return "Token (RightPar)";
		}
		// ドット
		if (this.kind == Kind.DOT) {
			return "Token (Dot)";
		}
		// 文字列
		if (this.kind == Kind.STRING) {
			return "Token (String, " + this.stringValue + ")";
		}
		// クォート(')
		if (this.kind == Kind.QUOTE) {
			return "Token (Quote)";
		}
		return "Token (Unknown)";
	}
}
