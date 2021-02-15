package lisp.eval;

public class Write implements Subroutine {
	private static final Write write = new Write();

	public static Write getInstance() {
		return write;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) {
		if (sexp instanceof EmptyList) {
		}
		if (((ConsCell) sexp).size() != 1) {
		}
		System.out.print(((ConsCell) sexp).getCar());
		return Undef.getInstance();
	}

	@Override
	public String toString() {
		return "#<subr write>";
	}
}