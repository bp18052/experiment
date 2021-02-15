package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

public class NewLine implements Subroutine {
	private static final NewLine newLine = new NewLine();

	public static NewLine getInstance() {
		return newLine;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {

		if (sexp instanceof EmptyList) {
			System.out.println("");
			return Undef.getInstance();
		}
		// エラー
		throw new ArgumentException("output port required, but got " + sexp);
	}

	@Override
	public String toString() {
		return "#<subr newline>";
	}
}