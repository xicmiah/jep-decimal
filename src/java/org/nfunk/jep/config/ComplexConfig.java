package org.nfunk.jep.config;

import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.function.*;
import org.nfunk.jep.function.doubleval.Abs;
import org.nfunk.jep.type.Complex;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DPavlov
 */
public class ComplexConfig implements FunctionConstantConfig {

    private FunctionTable funTab = new FunctionTable();
    private Map<String, Object> constants;

    public ComplexConfig() {
        initFunctions();
        initConstants();
    }

    public FunctionTable getFunctions() {
        return funTab;
    }

    public Map<String,Object> getConstants(){
        return constants;
    }

    private void initConstants() {
        constants = new HashMap<String, Object>(1);
        constants.put("i", new Complex(0,1));
    }

    public void initFunctions() {
        funTab.put("re", new Real());
        funTab.put("im", new Imaginary());
        funTab.put("arg", new Arg());
        funTab.put("cmod", new Abs());
        funTab.put("complex", new ComplexPFMC());
        funTab.put("polar", new Polar());
        funTab.put("conj", new Conjugate());
    }
}
