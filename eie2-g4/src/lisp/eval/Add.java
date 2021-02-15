package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

public class Add implements Subroutine {
	private static final Add add = new Add();

	public static Add getInstance() {
		return add;
	}

	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		Number number = (Number) Int.valueOf(0);
		// 引数なし
		if (sexp instanceof EmptyList) {
			return number;
		}
		// 引数が１個
		if (!(sexp instanceof ConsCell)) {
		}
		int size = ((ConsCell) sexp).size();
		if (size == 1) {
			return ((ConsCell) sexp).get(0);
		}
		// 引数が２個以上
		for (int i = 0; i < size; i++) {
			SExpression arg = ((ConsCell) sexp).get(i);
			if (!(arg instanceof Number)) {
				if (i == 0) {
					// 引数が数字でない場合はエラー
					throw new ArgumentException(
							"operation + is not defined between" + arg + " and " + ((ConsCell) sexp).get(1));
				}
				// 引数が数字でない場合はエラー
				throw new ArgumentException("operation + is not defined between " + number + " and " + arg);
			}
			// 引数が数値
			number = number.add((Number) arg);
		}
		return number;
	}

	public String toString() {
		return "#<subr +>";
	}
}
