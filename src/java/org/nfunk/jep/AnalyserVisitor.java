package org.nfunk.jep;

import org.nfunk.jep.validation.Scope;
import org.nfunk.jep.validation.ValidationException;

/**
 * Visitor which checks variable scope
 * All visit() methods:
 * 1) return scope after their evaluation
 * 2) accept data parameter as Scope instance
 */
public class AnalyserVisitor implements ParserVisitor {
	protected JEP jep;

	public AnalyserVisitor(JEP jep) {
		this.jep = jep;
	}

	/**
	 * Visit Start node. Extends scope with child scopes
	 *
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
	 *
	 * @param node visited node
	 * @param data scope
	 * @return extended scope
	 * @throws ParseException
	 */
	public Object visit(ASTFunNode node, Object data) throws ParseException {
		Scope scope = (Scope) data;
		if (jep.getOperatorSet().getAssign().equals(node.getOperator())) {
			scope = extendScopeWithAssign(node, scope);
		} else {
			node.childrenAccept(this, scope);
		}

		return scope;
	}

	/**
	 * Extends scope for assign operator.
	 * Extends current scope with right subtree and left variable node.
	 *
	 * @param node  assignment node
	 * @param scope current scope
	 * @return extended scope
	 * @throws ParseException
	 */
	private Scope extendScopeWithAssign(ASTFunNode node, Scope scope) throws ParseException {
		scope = (Scope) node.jjtGetChild(1).jjtAccept(this, scope); // Right tree extends scope
		String variableName = ((ASTVarNode) node.jjtGetChild(0)).getName(); // Left child is VarNode, don't visit it
		return scope.with(variableName); // Extend with assigned variable
	}

	/**
	 * Visit variable node. Checks that scope contains this variable
	 *
	 * @param node visited node
	 * @param data scope
	 * @return same scope
	 * @throws ValidationException if scope doesn't contain this variable
	 */
	public Object visit(ASTVarNode node, Object data) throws ValidationException {
		Scope scope = (Scope) data;
		String variable = node.getName();
		if (!scope.contains(variable)) {
			throw new ValidationException("Evaluation of undefined variable " + variable);
		} else {
			return scope;
		}

	}

	public Object visit(ASTConstant node, Object scope) throws ParseException {
		return scope;
	}

	public Object visit(SimpleNode node, Object scope) {
		return scope;
	}
}
