package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

/*特殊形式Lambda*/

public class Lambda implements SpecialForm {
	private static final Lambda lambda = new Lambda();

	public static Lambda getInstance() {
		return lambda;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("lambda"));
		if (!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed lambda: " + errorListBuilder.build().toString());
		}
		if (((ConsCell) sexp).size() < 2) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed lambda: " + errorListBuilder.build().toString());

		}

		SExpression params = ((ConsCell) sexp).get(0);
		SExpression body = ((ConsCell) sexp).getCar();

		return Closure.getInstance(body, params, environment);
	}

	@Override
	public String toString() {
		return "#<syntax lambda>";
	}
}
