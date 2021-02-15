package lisp.eval;

import lisp.exception.LispException;

/**
 * 特殊形式
 * 
 * @author tetsuya
 *
 */
interface SpecialForm extends SExpression {
	public SExpression apply(SExpression sexp, Environment environment) throws LispException;

//if,quote,lambda,define,set,let

}
