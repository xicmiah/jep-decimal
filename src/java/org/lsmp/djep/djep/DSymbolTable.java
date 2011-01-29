/* @author rich
 * Created on 23-Nov-2003
 */
package org.lsmp.djep.djep;

import org.lsmp.djep.xjep.XSymbolTable;
import org.nfunk.jep.SymbolTable;
import org.nfunk.jep.Variable;
import org.nfunk.jep.VariableFactory;

/**
 * A SymbolTable which works with partial derivatives of variables.
 * Closely linked with
 * {@link DVariableFactory DVariableFactory}
 *
 * @author Rich Morris
 *         Created on 23-Nov-2003
 */
public class DSymbolTable extends XSymbolTable {
    private static final long serialVersionUID = 6350706295032274871L;


    public DSymbolTable(VariableFactory varFac) {
        super(varFac);
    }

    /**
     * Creates a new SymbolTable with the same variable factory as this.
     */
    public SymbolTable newInstance() {
        return new DSymbolTable(this.getVariableFactory());
    }

    public PartialDerivative getPartialDeriv(String name, String dnames[]) {
        DVariable var = (DVariable) getVar(name);
        return var.getDerivative(dnames);
    }


    public void clearValues() {
        for (Variable variable : this.getVariables()) {
            DVariable var = (DVariable) variable;
            var.invalidateAll();
        }
    }
}
