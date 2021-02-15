package lisp.eval;

import java.util.HashMap;
import java.util.Map;

import lisp.exception.LispException;
import lisp.exception.UnboundException;

/**
 * 環境
 * 
 * @author tetsuya
 *
 */
public class Environment {
	private final Map<Symbol, SExpression> frame = new HashMap<>();
	private final Environment next;

	public Environment(Environment next) {
		this.next = next;
	}

	public Map<Symbol, SExpression> getFrame() {
		return this.frame;
	}

	private static Environment searchFrameOf(Environment environment, Symbol symbol) throws LispException {
		for (Environment e = environment; e != null; e = e.next) {
			if (e.frame.containsKey(symbol)) {
				return e;
			}
		}
		throw new UnboundException("unbound variable: " + symbol.toString());
	}

	/**
	 * 変数（シンボル）が束縛されている値を返す。
	 * 
	 * @param symbol シンボル
	 * @return シンボルが束縛されている値
	 */
	public SExpression getValueOf(Symbol symbol) throws LispException {
		return searchFrameOf(this, symbol).getFrame().get(symbol);
	}

	/**
	 * 変数束縛
	 * 
	 * @param symbol シンボル
	 * @param sexp   束縛する値
	 */
	public SExpression define(Symbol symbol, SExpression sexp) {
		frame.put(symbol, sexp);
		return sexp;
	}

	/**
	 * 変数の値の再定義
	 * 
	 * @param symbol シンボル
	 * @param sexp   束縛する値
	 */
	public SExpression set(Symbol symbol, SExpression sexp) throws LispException {
		searchFrameOf(this, symbol).getFrame().put(symbol, sexp);
		return sexp;
	}
}
