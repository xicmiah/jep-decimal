/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/
package org.lsmp.djep.xjep;

import org.nfunk.jep.SymbolTable;
import org.nfunk.jep.Variable;
import org.nfunk.jep.VariableFactory;

/**
 * An extension of the symbol table with a few new features.
 *
 * @author Rich Morris
 *         Created on 18-Mar-2004
 */
public class XSymbolTable extends SymbolTable {
    /**
     *
     */
    private static final long serialVersionUID = 741560154912130566L;

    /**
     * Create a new XSymbolTable with the given variable factory.
     */
    public XSymbolTable(VariableFactory varFac) {
        super(varFac);
    }

    /**
     * Creates a new SymbolTable with the same variable factory as this.
     */
    public SymbolTable newInstance() {
        return new XSymbolTable(this.getVariableFactory());
    }

    /**
     * Prints the contents of the symbol table displaying its equations and value.
     */
    public void print(PrintVisitor pv) {
        for (Variable variable : this.getVariables()) {
            XVariable var = (XVariable) variable;
            pv.append(var.toString(pv) + "\n");
            // TODO watch out for possible conflict with overriding pv's string buffer
        }
    }

    /**
     * Copy the values of all constants into this from the supplied symbol table.
     */
    public void copyConstants(SymbolTable symTab) {
        for (Variable var : symTab.getVariables()) {
            if (var.isConstant())
                this.addConstant(var.getName(), var.getValue());
        }
    }
}
