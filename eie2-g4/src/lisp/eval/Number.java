package lisp.eval;

import lisp.exception.LispException;

public interface Number extends SExpression {
	// 引数に与えられた数値との加算を行う
	public Number add(Number number);

	// 引数に与えられた数値との減算を行う
	public Number subtract(Number number);

	// 引数に与えられた数値との乗算を行う
	public Number multiple(Number number);

	// 引数に与えられた数値との除算を行う
	public Number divide(Number number) throws LispException;

	// 引数に与えられた数値との大小比較を行う
	public boolean small(Number number);

	// 引数に与えられた数値との大小比較を行う
	public boolean big(Number number);

	// 引数に与えられた数値との大小比較を行う
	public boolean smallEqual(Number number);

	// 引数に与えられた数値との大小比較を行う
	public boolean bigEqual(Number number);
}