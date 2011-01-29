package org.nfunk.jep.config;

import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.OperatorSet;
import org.nfunk.jep.function.*;
import org.nfunk.jep.function.doubleval.Abs;
import org.nfunk.jep.function.doubleval.Ceil;
import org.nfunk.jep.function.doubleval.Floor;
import org.nfunk.jep.function.doubleval.Round;
import org.nfunk.jep.function.operator.doubleval.Modulus;
import org.nfunk.jep.function.operator.doubleval.Power;
import org.nfunk.jep.type.DoubleNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author DPavlov
 */
public class DoubleConfig implements ExtendedConfig {

    private FunctionTable funTab;
    private Map<String, Object> constants;
    private OperatorSet operatorSet;
    private NumberFactory<Double> numberFactory;

    public DoubleConfig() {
        addStandardFunctions();
        initConstants();
        operatorSet = new OperatorSet();
        numberFactory = new DoubleNumberFactory();
    }

    public OperatorSet getOperatorSet() {
        return operatorSet;
    }

    public NumberFactory getNumberFactory() {
        return numberFactory;
    }

    public FunctionTable getFunctions() {
        return funTab;
    }

    public Map<String,Object> getConstants(){
        return constants;
    }

    private void initConstants() {
        constants = new HashMap<String, Object>();
        constants.put("e", Math.E);
        constants.put("pi", Math.PI);
    }

    private void addStandardFunctions() {
        //add functions to Function Table
        funTab = new FunctionTable();
        
        funTab.put("sin", new Sine());
        funTab.put("cos", new Cosine());
        funTab.put("tan", new Tangent());
        funTab.put("asin", new ArcSine());
        funTab.put("acos", new ArcCosine());
        funTab.put("atan", new ArcTangent());
        funTab.put("atan2", new ArcTangent2());

        funTab.put("sinh", new SineH());
        funTab.put("cosh", new CosineH());
        funTab.put("tanh", new TanH());
        funTab.put("asinh", new ArcSineH());
        funTab.put("acosh", new ArcCosineH());
        funTab.put("atanh", new ArcTanH());

        funTab.put("log", new Logarithm());
        funTab.put("ln", new NaturalLogarithm());
        funTab.put("exp", new Exp());
        funTab.put("pow", new Power());

        funTab.put("sqrt", new SquareRoot());
        funTab.put("abs", new Abs());
        funTab.put("mod", new Modulus());
        funTab.put("sum", new Sum());

        funTab.put("rand", new org.nfunk.jep.function.Random());

        funTab.put("if", new If());
        funTab.put("str", new Str());

        funTab.put("binom", new Binomial());

        funTab.put("round", new Round());
        funTab.put("floor", new Floor());
        funTab.put("ceil", new Ceil());
    }
}
