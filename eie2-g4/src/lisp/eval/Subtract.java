package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

public class Subtract implements Subroutine {
	private static final Subtract subtract = new Subtract();

	public static Subtract getInstance() {
		return subtract;
	}

	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		// 引数がない場合
		if (sexp instanceof EmptyList) {
			throw new ArgumentException("procedure requires at least one argument: (-)");
		}
		SExpression car = ((ConsCell) sexp).getCar();

		// 引数が１個の場合
		if (((ConsCell) sexp).size() == 1) {
			// 引数が数値でない場合
			if (!(car instanceof Number)) {
				throw new ArgumentException("operation - is not defined on object " + car);
			}

			return ((Number) car).multiple((Number) Int.valueOf(-1));
		}

		// 引数が２個以上の場合
		// 引数が数値でない場合
		if (!(car instanceof Number)) {
			throw new ArgumentException(
					"operation - is not defined between " + car + " and " + ((ConsCell) sexp).get(1));
		}
		Number number = (Number) car;
		SExpression tmp = ((ConsCell) sexp).getCdr();
		while (tmp instanceof ConsCell) {
			SExpression arg = ((ConsCell) tmp).getCar();
			if (!(arg instanceof Number)) {
				throw new ArgumentException("operation - is not defined between " + number + " and " + arg);
			}
			number = number.subtract((Number) arg);
			tmp = ((ConsCell) tmp).getCdr();
		}
		return number;
	}

	public String toString() {
		return "#<subr ->";
	}
}
