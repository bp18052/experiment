package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

public class Map implements Subroutine {
	private static final Map map = new Map();

	public static Map getInstance() {
		return map;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {
		if (!(sexp instanceof ConsCell)) {
			throw new ArgumentException("wrong number of arguments for " + this + " (required 2, you input 0)");
		}
		SExpression proc = ((ConsCell) sexp).getCar();
		SExpression lists = ((ConsCell) sexp).getCdr();
		int min = -1;
		for (int i = 0; i < ((ConsCell) lists).size(); i++) {
			SExpression tmpList = ((ConsCell) lists).get(i);
			if (!(tmpList instanceof ConsCell)) {
				throw new ArgumentException("argument lists contained improper things");
			}
			if (!((ConsCell) tmpList).isList()) {
				throw new ArgumentException("argument lists contained improper things");
			}
			int size = ((ConsCell) tmpList).size();
			if (min == -1) {
				min = size;
				continue;
			}
			if (size < min) {
				min = size;
			}
		}

		ConsCell.ListBuilder listBuilder = ConsCell.builder();

		int listsSize = ((ConsCell) lists).size();
		for (int elNum = 0; elNum < min; elNum++) {
			ConsCell.ListBuilder listBuilderElement = ConsCell.builder();
			listBuilderElement.tail(proc);
			for (int listNum = 0; listNum < listsSize; listNum++) {
				SExpression list = ((ConsCell) lists).get(listNum);
				if (!(list instanceof ConsCell)) {
					throw new ArgumentException("argument lists contained improper things");
				}
				SExpression element = ((ConsCell) list).get(elNum);
				listBuilderElement.tail(element);
			}
			SExpression evaled = Evaluator.eval(listBuilderElement.build(), environment);
			listBuilder.tail(evaled);
		}
		return listBuilder.build();
	}

	@Override
	public String toString() {
		return "#<subr map>";
	}
}