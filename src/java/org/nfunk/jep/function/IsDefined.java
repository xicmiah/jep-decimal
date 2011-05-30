package org.nfunk.jep.function;

import org.nfunk.jep.ASTVarNode;
import org.nfunk.jep.EvaluatorI;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;


/**
 * Function isDefined(var) - returns if variable has usable value
 */
public class IsDefined extends PostfixMathCommand implements CallbackEvaluationI {

	private static final Boolean FALLBACK_VALUE = true;

	public IsDefined() {
		super();
		numberOfParameters = 1;
	}

	/**
	 * Return, if variable is defined and has non-null value
	 * Falls back to true on non-variable argument
	 * 
	 * @param node The current node
	 * @param pv   The visitor, can be used evaluate the children
	 * @return false if variable is not defined or has null value, true otherwise
	 * @throws ParseException
	 */
	public Boolean evaluate(Node node, EvaluatorI pv) throws ParseException {
		Node argument = node.jjtGetChild(0);

		if (argument instanceof ASTVarNode) {
			ASTVarNode varNode = (ASTVarNode) argument;
			return varNode.getVar() != null && varNode.getVar().getValue() != null;
		}
		
		return FALLBACK_VALUE;
	}
}
