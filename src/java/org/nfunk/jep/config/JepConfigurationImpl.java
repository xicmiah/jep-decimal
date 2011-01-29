package org.nfunk.jep.config;

import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.OperatorSet;
import org.nfunk.jep.SymbolTable;
import org.nfunk.jep.type.NumberFactory;

/**
 * @author DPavlov
 */
public class JepConfigurationImpl implements JepConfiguration{

    private FunctionTable functions;

    private SymbolTable symbolTable;

    private OperatorSet operatorSet;

    private NumberFactory numberFactory;

    private boolean allowUndeclaredVariables;

    private boolean traverse;

    private boolean useImplicitMultiplication;

    private boolean allowAssignment;

    public JepConfigurationImpl(){}

    public JepConfigurationImpl(JepConfiguration config){
        this.functions = config.getFunctions();
        this.symbolTable = config.getInitialConstants();
        this.operatorSet = config.getOperators();
        this.numberFactory = config.getNumberFactory();
        this.allowUndeclaredVariables = config.isAllowUndeclaredVariables();
        this.traverse = config.isTraverse();
        this.useImplicitMultiplication = config.isUseImplicitMultiplication();
        this.allowAssignment = config.isAllowAssignment();
    }

    public JepConfigurationImpl(boolean traverse, boolean allowUndeclaredVariables,
                                boolean useImplicictMultiplication, boolean allowAssignment){
        this.traverse = traverse;
        this.allowUndeclaredVariables = allowUndeclaredVariables;
        this.useImplicitMultiplication = useImplicictMultiplication;
        this.allowAssignment = allowAssignment;
    }

    public FunctionTable getFunctions() {
        return functions;
    }

    public void setFunctions(FunctionTable functions) {
        this.functions = functions;
    }

    public SymbolTable getInitialConstants() {
        return symbolTable;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public OperatorSet getOperators() {
        return operatorSet;
    }

    public void setOperatorSet(OperatorSet operatorSet) {
        this.operatorSet = operatorSet;
    }

    public NumberFactory getNumberFactory() {
        return numberFactory;
    }

    public void setNumberFactory(NumberFactory numberFactory) {
        this.numberFactory = numberFactory;
    }

    public boolean isTraverse() {
        return traverse;
    }

    public void setTraverse(boolean traverse) {
        this.traverse = traverse;
    }

    public boolean isAllowUndeclaredVariables() {
        return allowUndeclaredVariables;
    }

    public void setAllowUndeclaredVariables(boolean allowUndeclaredVariables) {
        this.allowUndeclaredVariables = allowUndeclaredVariables;
    }

    public boolean isUseImplicitMultiplication() {
        return useImplicitMultiplication;
    }

    public void setUseImplicitMultiplication(boolean useImplicitMultiplication) {
        this.useImplicitMultiplication = useImplicitMultiplication;
    }

    public boolean isAllowAssignment() {
        return allowAssignment;
    }

    public void setAllowAssignment(boolean allowAssignment) {
        this.allowAssignment = allowAssignment;
    }
}