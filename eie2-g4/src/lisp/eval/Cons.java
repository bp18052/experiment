package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

public class Cons implements Subroutine {

	private static final Cons cons = new Cons();

	public static Cons getInstance() {
		return cons;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {

		if (!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for" + this + "(required 2,got 0)");
		}

		int size = ((ConsCell) sexp).size();
		if (size != 2) {
			throw new ArgumentException("wrong number of arguments for " + this + "(requires 2,got " + size + ")");
		}
		SExpression car = ((ConsCell) sexp).getCar();
		SExpression cdr = ((ConsCell) ((ConsCell) sexp).getCdr()).getCar();
		return ConsCell.getInstance(car, cdr);
	}

	@Override
	public String toString() {

		return "#<subr cons>";
	}

}
