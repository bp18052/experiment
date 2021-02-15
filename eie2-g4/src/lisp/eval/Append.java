package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

public class Append implements Subroutine {
	private static final Append append = new Append();

	public static Append getInstance() {
		return append;
	}

	private SExpression append(SExpression list1, SExpression list2) throws LispException {
		if (list1 instanceof EmptyList) {
			return list2;
		}
		if (!(list1 instanceof ConsCell)) {
			throw new ArgumentException("list required,but got " + list1);
		}
		return ConsCell.getInstance(((ConsCell) list1).getCar(), append(((ConsCell) list1).getCdr(), list2));
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if (!(sexp instanceof ConsCell)) {
			return sexp;
		}
		if (((ConsCell) sexp).size() == 1) {
			return sexp;
		}

		SExpression appendedList = ((ConsCell) sexp).get(0);
		for (int i = 0; i < ((ConsCell) sexp).size() - 1; i++) {
			appendedList = append(appendedList, ((ConsCell) sexp).get(i + 1));
		}
		return appendedList;
	}

	@Override
	public String toString() {
		return "#<subr append>";
	}
}
