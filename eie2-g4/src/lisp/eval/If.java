package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class If implements SpecialForm {
	private static final If lispIf = new If();

	public static If getInstance() {
		return lispIf;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("if"));
		if (!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed if: " + errorListBuilder.build().toString());
		}
		if (((ConsCell) sexp).size() == 3) {
			SExpression predicate = ((ConsCell) sexp).getCar();
			SExpression evaled = Evaluator.eval(predicate, environment);
			if (!(evaled instanceof Bool)) {

			}
			if (((Bool) evaled).isValid()) {
				SExpression thenExpression = ((ConsCell) ((ConsCell) sexp).getCdr()).getCar();
				return Evaluator.eval(thenExpression, environment);
			}
			SExpression elseExpression = ((ConsCell) ((ConsCell) ((ConsCell) ((ConsCell) sexp).getCdr())).getCdr())
					.getCar();
			return Evaluator.eval(elseExpression, environment);
		}

		if (((ConsCell) sexp).size() == 2) {
			SExpression predicate = ((ConsCell) sexp).getCar();
			SExpression evaled = Evaluator.eval(predicate, environment);
			if (evaled instanceof Bool) {
				if (!((Bool) evaled).isValid()) {
					return Undef.getInstance();
				}
			}
			SExpression thenExpression = ((ConsCell) ((ConsCell) sexp).getCdr()).getCar();
			return Evaluator.eval(thenExpression, environment);
		}
		errorListBuilder.last(sexp);
		throw new SyntaxErrorException("malformed if: " + errorListBuilder.build().toString());
	}

	@Override
	public String toString() {
		return "#<syntax if>";
	}
}