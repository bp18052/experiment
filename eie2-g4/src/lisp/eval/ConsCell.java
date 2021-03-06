package lisp.eval;

public class ConsCell implements SExpression {

	private SExpression car;
	private SExpression cdr;

	// carを返す
	public SExpression getCar() {
		return this.car;
	}

	// cdrを返す
	public SExpression getCdr() {
		return this.cdr;
	}

	// carをセット
	public void setCar(SExpression sexp) {
		this.car = sexp;
	}

	// cdrをセット
	public void setCdr(SExpression sexp) {
		this.cdr = sexp;
	}

	private ConsCell(SExpression car, SExpression cdr) {
		this.car = car;
		this.cdr = cdr;
	}

	/**
	 * Cons Cell（ドット対）を構築する。
	 * 
	 * @param car car部
	 * @param cdr cdr部
	 * @return 指定されたcar部とcdr部で構成されるCons Cell（ドット対）
	 */
	public static ConsCell getInstance(SExpression car, SExpression cdr) {
		return new ConsCell(car, cdr);
	}

	@Override
	public String toString() {
		if (!(this.car instanceof ConsCell) && !(this.cdr instanceof ConsCell)) {
			if (this.cdr instanceof EmptyList) {
				return "(" + this.car + ")";
			}
			return "(" + this.car + " . " + this.cdr + ")";
		}
		SExpression tmp = this;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("(");
		while (tmp instanceof ConsCell) {
			stringBuilder.append(((ConsCell) tmp).getCar());
			stringBuilder.append(" ");
			tmp = ((ConsCell) tmp).getCdr();
		}
		if (!(tmp instanceof EmptyList)) {
			stringBuilder.append(".");
			stringBuilder.append(" ");
			stringBuilder.append(tmp);
			stringBuilder.append(" ");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append(")");
		return stringBuilder.toString();
	}

	public int size() {
		int size = 0;
		SExpression sexp = this;
		while (!(sexp instanceof EmptyList)) {
			size++;
			sexp = ((ConsCell) sexp).getCdr();
		}
		return size;
	}

	public SExpression get(int n) {
		if (n == 0) {
			return this.car;
		}
		if (this.cdr instanceof EmptyList) {
			return EmptyList.getInstance();
		}
		return ((ConsCell) this.cdr).get(n - 1);
	}

	public boolean isList() {
		SExpression tmp = this;
		while (tmp instanceof ConsCell) {
			tmp = ((ConsCell) tmp).getCdr();
		}
		if (tmp instanceof EmptyList) {
			return true;
		}
		return false;
	}

	public static class ListBuilder {
		SExpression head = EmptyList.getInstance();
		SExpression tail = EmptyList.getInstance();

		public ListBuilder head(SExpression sexp) {
			head = ConsCell.getInstance(sexp, head);
			if (!(tail instanceof ConsCell)) {
				tail = head;
			}
			return this;
		}

		public ListBuilder tail(SExpression sexp) {
			if (tail instanceof ConsCell) {
				ConsCell consCell = (ConsCell) tail;
				consCell.setCdr(ConsCell.getInstance(sexp, consCell.getCdr()));
				tail = ((ConsCell) tail).getCdr();
			} else {
				tail = ConsCell.getInstance(sexp, tail);
				head = tail;
			}
			return this;
		}

		public ListBuilder last(SExpression sexp) {
			if (tail instanceof ConsCell) {
				((ConsCell) tail).setCdr(sexp);
			} else {
				tail = sexp;
				head = tail;
			}
			return this;
		}

		public SExpression build() {
			return head;
		}

	}

	public static ListBuilder builder() {
		return new ListBuilder();
	}

}
