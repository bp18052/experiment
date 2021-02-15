package lisp.eval;

public class Quote implements SpecialForm {
	private static final Quote quote = new Quote();

	public static Quote getInstance() {
		return quote;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		return ((ConsCell) sexp).getCar();
	}

	@Override
	public String toString() {
		return "#<syntax quote>";
	}
}