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

    private Map<String, Object> constants = new HashMap<String, Object>();

    private VariableFactory variableFactory;

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
        SymbolTable symbolTable = createSymbolTable();
        config.setSymbolTable(symbolTable);

        config.setOperatorSet(operatorSet);

        return config;
    }

    private SymbolTable createSymbolTable() {
        SymbolTable symbolTable = new SymbolTable(variableFactory);
        for (Map.Entry<String,Object> entry : constants.entrySet()){
            symbolTable.addConstant(entry.getKey(), entry.getValue());
        }
        return symbolTable;
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
        add(config);

        return this;
    }

    public ConfigurationBuilder add(FunctionConstantConfig config){
        FunctionTable funTab = config.getFunctions();
        for (String name : funTab.functionNames()){
            functions.put(name, funTab.get(name));
        }
        this.constants.putAll(config.getConstants());

        return this;
    }


    public ConfigurationBuilder setVariableFactory(VariableFactory variableFactory) {
        this.variableFactory = variableFactory;
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
