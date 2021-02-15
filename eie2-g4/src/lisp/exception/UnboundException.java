package lisp.exception;

@SuppressWarnings("serial")
public class UnboundException extends LispException {
	public UnboundException(String msg) {
		super(msg);
	}
}
