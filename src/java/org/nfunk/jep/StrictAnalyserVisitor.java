package org.nfunk.jep;

import org.nfunk.jep.validation.Scope;
import org.nfunk.jep.validation.ValidationException;

/**
 * Strict version of AnalyserVisitor, which forbids inner declarations
 */
public class StrictAnalyserVisitor implements ParserVisitor {
	/**
	 * AnalyserVisitor delegate
	 */
	private final AnalyserVisitor delegate;
	private final JEP jep;

	public StrictAnalyserVisitor(JEP jep) {
		this.jep = jep;
		delegate = new AnalyserVisitor(this.jep);
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
	 * Visit function node, checks conditionals with closed scope
	 * @param node visited node
	 * @param data scope
	 * @return extended scope
	 * @throws ParseException
	 */
	public Object visit(ASTFunNode node, Object data) throws ParseException {
		if (jep.getOperatorSet().getCond().equals(node.getOperator())) {
			Scope scope = (Scope) data;
			node.childrenAccept(delegate, scope.closed()); // Evaluate children with closed scope
			return data;
		} else {
			return delegate.visit(node, data);
		}
	}

	public Object visit(ASTVarNode node, Object data) throws ValidationException {
		return delegate.visit(node, data);
	}

	public Object visit(ASTConstant node, Object scope) throws ParseException {
		return delegate.visit(node, scope);
	}

	public Object visit(SimpleNode node, Object scope) {
		return delegate.visit(node, scope);
	}

}
