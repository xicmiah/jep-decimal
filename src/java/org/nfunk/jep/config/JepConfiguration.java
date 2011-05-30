package org.nfunk.jep.config;

import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.OperatorSet;
import org.nfunk.jep.SymbolTable;
import org.nfunk.jep.type.NumberFactory;

/**
 * Jep configuration. Jep instance can use all elements of this config as is
 * without need to copy elements for private use
 *
 * @author DPavlov
 */
public interface JepConfiguration {

    /**
     *
     * @return return list of initial functions
     */
    public FunctionTable getFunctions();

    /**
     *
     * @return list of initials symbols: variables and constants
     */
    public SymbolTable getInitialConstants();

    /**
     *
     * @return operators
     */
    public OperatorSet getOperators();

    /**
     *
     * @return number factory
     */
    public NumberFactory getNumberFactory();

    /**
     *
     * @return is traverse
     */
    public boolean isTraverse();

    /**
     *
     * @return is undeclared variables allowed during calculations
     */
    public boolean isAllowUndeclaredVariables();

    /**
     *
     * @return is implicit multiplications allowed
     */
    public boolean isUseImplicitMultiplication();    

    public boolean isAllowAssignment();

	public boolean isAllowNullValues();
}
