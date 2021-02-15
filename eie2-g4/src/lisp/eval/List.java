package lisp.eval;

public class List implements Subroutine {
	private static final List list = new List();

	public static List getInstance() {
		return list;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		return sexp;
	}

	@Override
	public String toString() {
		return "#<subr list>";
	}

}
