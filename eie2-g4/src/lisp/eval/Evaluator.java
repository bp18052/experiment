package lisp.eval;

import lisp.exception.LispException;

/**
 * 評価器
 * 
 * @author tetsuya
 *
 */
public class Evaluator {

	/**
	 * <p>
	 * 引数の環境の下で引数のS式を評価する。
	 * </p>
	 * 
	 * <p>
	 * S式xの評価値をE[x]とすると、E[x]は次のように定義される。
	 * </p>
	 * <ul>
	 * <li>xが整数値の場合、E[x] = x</li>
	 * <li>xが真理値の場合、E[x] = x</li>
	 * <li>xが空リストの場合、E[x] = 空リスト</li>
	 * <li>xが未定義値の場合、E[x] = 未定義値</li>
	 * <li>xがクロージャの場合、E[x] = x</li>
	 * <li>xがサブルーチンの場合、E[x] = x</li>
	 * <li>xが特殊形式の場合、E[x] = x</li>
	 * <li>xが記号の場合、E[x] = 環境の下で記号xに束縛された値</li>
	 * <li>xが空リストではないリスト(x0 x1 ... xn)の場合
	 * <ul>
	 * <li>E[x0]が特殊形式の場合、特殊形式を引数x1, ..., xn に適用した結果</li>
	 * <li>E[x0]がサブルーチン（組み込み手続き）の場合、サブルーチンを引数E[x1], ..., E[xn]に適用した結果</li>
	 * <li>E[x0]がクロージャの場合、クロージャを引数E[x1], ..., E[xn]に適用した結果</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * 
	 * @param sexp S式
	 * @param env  環境
	 */
	public static SExpression eval(SExpression sexp, Environment env) throws LispException {
		// 空リストを評価
		if (sexp instanceof EmptyList) {
			return sexp;
		}
		// 整数を評価
		else if (sexp instanceof Int) {
			return sexp;
		}

		// 浮動小数点数を評価
		if (sexp instanceof Flo) {
			return sexp;
		}

		else if (sexp instanceof Flo) {
			return sexp;
		}
		// 記号を評価
		else if (sexp instanceof Symbol) {
			return env.getValueOf((Symbol) sexp);
		}
		// ConsCellを評価
		else if (sexp instanceof ConsCell) {
			SExpression car = eval(((ConsCell) sexp).getCar(), env);
			SExpression cdr = ((ConsCell) sexp).getCdr();
			if (car instanceof SpecialForm) {
				return ((SpecialForm) eval(car, env)).apply(((ConsCell) sexp).getCdr(), env);
			} else if (car instanceof Subroutine) {
				return ((Subroutine) car).apply(evalArgu(cdr, env), env);
			} else if (car instanceof Closure) {
				return ((Closure) car).apply(evalArgu(cdr, env), env);
			}
		}
		return sexp;

	}

	private static SExpression evalArgu(SExpression sexp, Environment env) throws LispException {
		if (sexp instanceof EmptyList) {
			return EmptyList.getInstance();
		}
		ConsCell.ListBuilder listBuilder = ConsCell.builder();
		for (int i = 0; i < ((ConsCell) sexp).size(); i++) {
			listBuilder.tail(eval(((ConsCell) sexp).get(i), env));
		}
		return listBuilder.build();
	}
}
