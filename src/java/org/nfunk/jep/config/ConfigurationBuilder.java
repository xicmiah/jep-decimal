package org.nfunk.jep.config;

import org.nfunk.jep.*;
import org.nfunk.jep.function.PostfixMathCommandI;
import org.nfunk.jep.type.NumberFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Builder for constructing jep configs allows
 *
 * @author DPavlov
 */
public class ConfigurationBuilder {

    private Map<String, PostfixMathCommandI> functions = new HashMap<String, PostfixMathCommandI>();

    private SymbolTable symbolTable;

    private OperatorSet operatorSet;

    private NumberFactory numberFactory;

    private boolean allowUndeclaredVariables;

    private boolean traverse;

    private boolean useImplicitMultiplication;

    private boolean allowAssignment;

    public JepConfiguration createConfig(){
        JepConfigurationImpl config = new JepConfigurationImpl(traverse, allowUndeclaredVariables,
                useImplicitMultiplication, allowAssignment);
        
        config.setNumberFactory(numberFactory);

        config.setFunctions(copyFunctions());
        config.setSymbolTable(symbolTable.copyInstance());
        config.setOperatorSet(operatorSet);

        return config;
    }

    private FunctionTable copyFunctions() {
        FunctionTable table = new FunctionTable();
        for (Map.Entry<String,PostfixMathCommandI> entry : functions.entrySet()){
            table.put(entry.getKey(), entry.getValue());
        }
        return table;
    }

    public ConfigurationBuilder initWith(ExtendedConfig config){
        numberFactory = config.getNumberFactory();
        operatorSet = config.getOperatorSet();
        addFunctionsAndConstants(config);

        return this;
    }

    public ConfigurationBuilder addFunctionsAndConstants(FunctionConstantConfig config){
        FunctionTable funTab = config.getFunctions();
        for (String name : funTab.functionNames()){
            functions.put(name, funTab.get(name));
        }
        for (Map.Entry<String, Object> constants : config.getConstants().entrySet()){
            symbolTable.addConstant(constants.getKey(), constants.getValue());
        }

        return this;
    }


    public ConfigurationBuilder setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        return this;
    }

    public ConfigurationBuilder setOperatorSet(OperatorSet operatorSet) {
        this.operatorSet = operatorSet;
        return this;
    }

    public ConfigurationBuilder setNumberFactory(NumberFactory numberFactory) {
        this.numberFactory = numberFactory;
        return this;
    }

    public ConfigurationBuilder setAllowUndeclaredVariables(boolean allowUndeclaredVariables) {
        this.allowUndeclaredVariables = allowUndeclaredVariables;
        return this;
    }

    public ConfigurationBuilder setTraverse(boolean traverse) {
        this.traverse = traverse;
        return this;
    }

    public ConfigurationBuilder setUseImplicitMultiplication(boolean useImplicitMultiplication) {
        this.useImplicitMultiplication = useImplicitMultiplication;
        return this;
    }

    public ConfigurationBuilder setAllowAssignment(boolean allowAssignment) {
        this.allowAssignment = allowAssignment;
        return this;
    }

}
