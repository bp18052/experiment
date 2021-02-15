package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/*組み込み手続きNull*/

public class AskNull implements Subroutine {
	private static final AskNull asknull = new AskNull();

	public static AskNull getInstance() {
		return asknull;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if (!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for " + this + " (required 1, got 0)");
		}

		int size = ((ConsCell) sexp).size();
		if (size != 1) {
			throw new ArgumentException("wrong number of arguments for " + this + " (required 1, got " + size + ")");
		}
		return (((ConsCell) sexp).get(0) instanceof EmptyList) ? Bool.valueOf(true) : Bool.valueOf(false);
	}

	@Override
	public String toString() {
		return "#<subr null?>";
	}

}
