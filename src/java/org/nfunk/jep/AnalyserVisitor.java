package org.nfunk.jep;

import org.nfunk.jep.validation.Scope;
import org.nfunk.jep.validation.ValidationException;

/**
 * Visitor which checks variable scope
 * All visit() methods:
 * 1) return scope after their evaluation
 * 2) accept data parameter as Scope instance
 */
public class AnalyserVisitor implements ParserVisitor{
	protected JEP jep;

	public AnalyserVisitor(JEP jep) {
		this.jep = jep;
	}

	/**
	 * Visit Start node. Extends scope with child scopes
	 * @param node visited node
	 * @param data scope
	 * @return scope with scopes from children
	 * @throws ParseException
	 */
	public Object visit(ASTStart node, Object data) throws ParseException {
		Scope scope = (Scope) data;

		for (Node child : node.children) {
			scope = (Scope) child.jjtAccept(this, scope); // Child evaluation extends scope
		}
		return scope;
	}

	/**
	 * Visit function node. Assign operator extends scope, others do not
	 * @param node visited node
	 * @param data scope
	 * @return extended scope
	 * @throws ParseException
	 */
	public Object visit(ASTFunNode node, Object data) throws ParseException {
		Scope extended = (Scope) data;
		if (node.getOperator().equals(jep.getOperatorSet().getAssign())) {
			extended = (Scope) node.jjtGetChild(1).jjtAccept(this, extended); // Right tree extends scope
			extended = extended.with(((ASTVarNode) node.jjtGetChild(0)).getName()); // Extend with assigned variable
		} else {
			node.childrenAccept(this, extended);
		}

		return extended;
	}

	/**
	 * Visit variable node. Checks scope
	 * @param node visited node
	 * @param data scope
	 * @return same scope
	 * @throws ValidationException if scope doesn't contain this variable
	 */
	public Object visit(ASTVarNode node, Object data) throws ValidationException {
		Scope scope = (Scope) data;
		if (!scope.contains(node.getName())) {
			throw new ValidationException("Evaluation of variable not in scope");
		} else {
			return scope;
		}

	}

	public Object visit(SimpleNode node, Object scope) {
		return scope;
	}

	public Object visit(ASTConstant node, Object scope) throws ParseException {
		return scope;
	}
}
