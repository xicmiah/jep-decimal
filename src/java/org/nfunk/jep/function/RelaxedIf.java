package org.nfunk.jep.function;

import org.nfunk.jep.EvaluatorI;
import org.nfunk.jep.EvaluatorVisitor;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.type.Complex;

public class RelaxedIf extends PostfixMathCommand implements CallbackEvaluationI {

	public RelaxedIf() {
		super();
		numberOfParameters = -1;
	}

	@Override
	public boolean checkNumberOfParameters(int n) {
		return n == 2 || n == 3;
	}

	public Object evaluate(Node node, EvaluatorI pv) throws ParseException {
		int numChildren = node.jjtGetNumChildren();
		if (!checkNumberOfParameters(numChildren)) {
			throw new ParseException("If operator must have 2 or 3 arguments");
		}

		Object condVal = pv.eval(node.jjtGetChild(0));
		boolean result;

		if (condVal instanceof Boolean) {
			result = (Boolean) condVal;
		} else if (condVal instanceof Complex) {
			result = ((Complex) condVal).re() > 0.0;
		} else if (condVal instanceof Number) {
			result = ((Number) condVal).doubleValue() > 0.0;
		} else {
			throw new ParseException("Condition in if operator must be double or complex");
		}

		if (result) {
			return pv.eval(node.jjtGetChild(1));
		} else if (numChildren == 3) {
			return pv.eval(node.jjtGetChild(2));
		} else
			return EvaluatorVisitor.NOTHING;
	}
}
