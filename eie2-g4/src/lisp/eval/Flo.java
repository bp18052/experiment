package lisp.eval;

import lisp.exception.ArgumentException;
import lisp.exception.LispException;

/**
 * 整数値
 * 
 * 
 *
 */
public class Flo implements SExpression, Number {
	private java.lang.Float value;

	public java.lang.Float getValue() {
		return value;
	}

	private Flo(java.lang.Float value) {
		this.value = value;
	}

	/**
	 * 整数値のインスタンスを得る。
	 * 
	 * @param value 浮動小数点数値
	 * @return 浮動小数点数値のインスタンス
	 */
	public static lisp.eval.Flo valueOf(float value) {
		return new lisp.eval.Flo(value);
	}

	@Override
	public Number add(Number number) {
		if (number instanceof Int) {
			return valueOf(value + ((Int) number).getValue());
		}
		return valueOf(value + ((lisp.eval.Flo) number).getValue());
	}

	@Override
	public Number subtract(Number number) {
		if (number instanceof Int) {
			return valueOf(value - ((Int) number).getValue());
		}
		return valueOf(value - ((lisp.eval.Flo) number).getValue());
	}

	@Override
	public Number multiple(Number number) {
		if (number instanceof Int) {
			return valueOf(value * ((Int) number).getValue());
		}
		return valueOf(value * ((lisp.eval.Flo) number).getValue());
	}

	@Override
	public Number divide(Number number) throws LispException {
		if (number instanceof Int) {
			if (((Int) number).getValue() == 0) {
				throw new ArgumentException("attempt to calculate a division by zero");
			}
			return valueOf(value / ((Int) number).getValue());
		}
		if (((lisp.eval.Flo) number).getValue() == 0) {
			throw new ArgumentException("attempt to calculate a division by zero");
		}
		return valueOf(value / ((lisp.eval.Flo) number).getValue());
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
		if ((obj instanceof lisp.eval.Flo) == false) {
			return false;
		}
		return this.value.equals(((lisp.eval.Flo) obj).getValue());
	}
}