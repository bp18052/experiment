package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;

public class Set implements SpecialForm {
	private static final Set set = new Set();

	public static Set getInstance() {
		return set;
	}

	@Override
	public SExpression apply(SExpression sexp, Environment environment) throws LispException {

		// 第一引数はSymbol
		// 第一引数のSymbolが環境にある時、第二引数を評価して代入する
		ConsCell.ListBuilder errorListBuilder = ConsCell.builder();
		errorListBuilder.tail(Symbol.getInstance("set!"));
		if (!(sexp instanceof ConsCell)) {
			throw new SyntaxErrorException("malformed set!: " + errorListBuilder.build().toString());
		}
		if (((ConsCell) sexp).size() != 2) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed set!: " + errorListBuilder.build().toString());
		}
		SExpression symbol = ((ConsCell) sexp).getCar();
		if (!(symbol instanceof Symbol)) {
			errorListBuilder.last(sexp);
			throw new SyntaxErrorException("malformed set!: " + errorListBuilder.build().toString());
		}
		SExpression exp = ((ConsCell) sexp).get(1);
		environment.set((Symbol) symbol, Evaluator.eval(exp, environment));
		return exp;
	}

	@Override
	public String toString() {
		return "#<syntax set!>";
	}
}