package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 整数値
 * 
 * @author tetsuya
 *
 */
public class Int implements SExpression, Number {
	private Integer value;

	public Integer getValue() {
		return value;
	}

	private Int(Integer value) {
		this.value = value;
	}

	/**
	 * 整数値のインスタンスを得る。
	 * 
	 * @param value 整数値
	 * @return 整数値のインスタンス
	 */
	public static Int valueOf(int value) {
		return new Int(value);
	}

	public Number modulo(Number number) {
		return valueOf(value % ((Int) number).getValue());
	}

	@Override
	public Number add(Number number) {
		if (number instanceof Int) {
			return valueOf(value + ((Int) number).getValue());
		}
		return lisp.eval.Flo.valueOf(value + ((lisp.eval.Flo) number).getValue());
	}

	@Override
	public Number subtract(Number number) {
		if (number instanceof Int) {
			return valueOf(value - ((Int) number).getValue());
		}
		return lisp.eval.Flo.valueOf(value - ((lisp.eval.Flo) number).getValue());
	}

	@Override
	public Number multiple(Number number) {
		if (number instanceof Int) {
			return valueOf(value * ((Int) number).getValue());
		}
		return lisp.eval.Flo.valueOf(value * ((lisp.eval.Flo) number).getValue());
	}

	@Override
	public Number divide(Number number) throws LispException {
		if (number instanceof Int) {
			if (((Int) number).getValue() == 0) {
				throw new ArgumentException("attempt to calculate a division by zero");
			}
			return lisp.eval.Flo.valueOf(value / (float) ((Int) number).getValue());
		}
		if (((lisp.eval.Flo) number).getValue() == 0) {
			throw new ArgumentException("attempt to calculate a division by zero");
		}
		return lisp.eval.Flo.valueOf(value / ((lisp.eval.Flo) number).getValue());
	}

	@Override
	public boolean small(Number number) {
		double comparedNumber = (number instanceof Int) ? ((Int) number).getValue()
				: ((lisp.eval.Flo) number).getValue();
		return (value < comparedNumber) ? true : false;
	}

	@Override
	public boolean big(Number number) {
		double comparedNumber = (number instanceof Int) ? ((Int) number).getValue()
				: ((lisp.eval.Flo) number).getValue();
		return (value > comparedNumber) ? true : false;
	}

	@Override
	public boolean smallEqual(Number number) {
		double comparedNumber = (number instanceof Int) ? ((Int) number).getValue()
				: ((lisp.eval.Flo) number).getValue();
		return (value <= comparedNumber) ? true : false;
	}

	@Override
	public boolean bigEqual(Number number) {
		double comparedNumber = (number instanceof Int) ? ((Int) number).getValue()
				: ((lisp.eval.Flo) number).getValue();
		return (value >= comparedNumber) ? true : false;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Int) == false) {
			return false;
		}
		return this.value.equals(((Int) obj).getValue());
	}
}
