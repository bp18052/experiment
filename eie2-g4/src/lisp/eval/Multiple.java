package lisp.eval;

import lisp.exception.ArgumentException;

public class Multiple implements Subroutine {
	private static final Multiple multiple = new Multiple();

	public static Multiple getInstance() {
		return multiple;
	}

	public SExpression apply(SExpression sexp, Environment environment) throws ArgumentException {
		Number number = (Number) Int.valueOf(1);
		SExpression tmp = sexp;
		while (tmp instanceof ConsCell) {
			SExpression car = ((ConsCell) tmp).getCar();
			// 引数が数値でない場合
			if (!(car instanceof Number)) {
				throw new ArgumentException(
						"operation * is not defined between " + car + " and " + ((ConsCell) sexp).get(1));
			}

			number = number.multiple((Number) car);
			tmp = ((ConsCell) tmp).getCdr();
		}
		return number;
	}

	public String toString() {
		return "#<subr *>";
	}
}
