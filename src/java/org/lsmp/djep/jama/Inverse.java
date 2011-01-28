/* @author rich
 * Created on 15-Feb-2005
 *
 * See LICENSE.txt for license information.
 */
package org.lsmp.djep.jama;

import org.lsmp.djep.vectorJep.Dimensions;
import org.lsmp.djep.vectorJep.function.UnaryOperatorI;
import org.lsmp.djep.vectorJep.values.Matrix;
import org.lsmp.djep.vectorJep.values.MatrixValueI;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * Find the inverses of a matrix.
 * Serves a wrapper around the Jama linear algebra function.
 *
 * @author Rich Morris
 *         Created on 15-Feb-2005
 * @see <a href="http://math.nist.gov/javanumerics/jama/">http://math.nist.gov/javanumerics/jama/</a>
 */
public class Inverse extends PostfixMathCommand implements UnaryOperatorI {
    public Inverse() {
        this.numberOfParameters = 1;
    }


    public void run(Stack s) throws ParseException {
        Object o = s.pop();
        if (!(o instanceof Matrix))
            throw new ParseException("inverse: can only be applied to a matrix");
        Jama.Matrix m = JamaUtil.toJama((Matrix) o);
        Jama.Matrix inv = m.inverse();
        Matrix res = JamaUtil.fromJama(inv);
        s.push(res);
    }

    public Dimensions calcDim(Dimensions ldim) {
        int rows = ldim.getFirstDim();
        int cols = ldim.getLastDim();
        if (rows < cols) return Dimensions.valueOf(rows, rows);
        else if (rows > cols) return Dimensions.valueOf(cols, cols);
        else return ldim;
    }

    public MatrixValueI calcValue(MatrixValueI res, MatrixValueI lhs)
            throws ParseException {
        if (!(lhs instanceof Matrix))
            throw new ParseException("inverse: can only be applied to a matrix");
        if (!(res instanceof Matrix))
            throw new ParseException("inverse: result should be a matrix");
        Jama.Matrix m = JamaUtil.toJama((Matrix) lhs);
        Jama.Matrix inv = m.inverse();
        JamaUtil.fromJama(inv, (Matrix) res);
        return res;
    }

}
