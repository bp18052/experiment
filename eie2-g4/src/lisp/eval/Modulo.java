package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/*組み込み手続きmodulo*/

public class Modulo implements Subroutine {
	private static final Modulo modulo = new Modulo();

	public static Modulo getInstance() {
		return modulo;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if (!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for " + this + " (required 2, got 0)");
		}

		int size = ((ConsCell) sexp).size();
		if (size != 2) {
			throw new ArgumentException("wrong number of arguments for " + this + " (required 2, got " + size + ")");
		}

		SExpression firstArg = ((ConsCell) sexp).get(0);
		if (!(firstArg instanceof Int)) {
			throw new ArgumentException("integer required, but got " + firstArg);
		}
		SExpression secondArg = ((ConsCell) sexp).get(1);
		if (!(secondArg instanceof Int)) {
			throw new ArgumentException("integer required, but got " + secondArg);
		}

		return ((Int) firstArg).modulo((Number) secondArg);
	}

	@Override
	public String toString() {
		return "#<subr modulo>";
	}

}