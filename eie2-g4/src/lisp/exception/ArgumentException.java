package lisp.exception;

@SuppressWarnings("serial")
public class ArgumentException extends LispException {
	public ArgumentException(String msg) {
		super(msg);
	}
}