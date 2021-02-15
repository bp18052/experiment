package lisp.eval;

import lisp.exception.LispException;

/**
 * 組み込み手続き
 * 
 * @author tetsuya
 *
 */
interface Subroutine extends SExpression {
	public SExpression apply(SExpression sexp, Environment env) throws LispException;
}
// add,substract,multiple,divide,modulo,sin,cos,cons,car,cdr,null,list,append,
// eqale,small,smalleqale,big,bigeqale,eq,not,write,newline,map,exit
