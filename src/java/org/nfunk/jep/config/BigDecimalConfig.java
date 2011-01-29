package org.nfunk.jep.config;

import org.nfunk.jep.BigDecimalOperatorSet;
import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.OperatorSet;
import org.nfunk.jep.function.*;
import org.nfunk.jep.function.bigdecimal.BigDecimalAbs;
import org.nfunk.jep.function.bigdecimal.BigDecimalCeil;
import org.nfunk.jep.function.bigdecimal.BigDecimalFloor;
import org.nfunk.jep.function.bigdecimal.BigDecimalRound;
import org.nfunk.jep.function.operator.AbstractAdd;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalAdd;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalModulus;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalPower;
import org.nfunk.jep.type.BigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DPavlov
 */
public class BigDecimalConfig implements ExtendedConfig {

    private FunctionTable funTab;
    private Map<String, Object> constants;
    private BigDecimalNumberFactory numberFactory;
    private BigDecimalOperatorSet operatorSet;

    public BigDecimalConfig(MathContext context) {
        this.numberFactory = new BigDecimalNumberFactory(context);
        this.operatorSet = new BigDecimalOperatorSet(numberFactory);
        addStandardFunctions();
        initConstants();
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

    public Map<String, Object> getConstants() {
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
        funTab.put("pow", initFunction(new BigDecimalPower()));

        funTab.put("sqrt", new SquareRoot());
        funTab.put("abs", initFunction(new BigDecimalAbs()));
        funTab.put("mod", initFunction(new BigDecimalModulus()));
        funTab.put("sum", new Sum((AbstractAdd) initFunction(new BigDecimalAdd())));

        funTab.put("rand", new org.nfunk.jep.function.Random());

        funTab.put("if", new If());
        funTab.put("str", new Str());

        funTab.put("binom", new Binomial());

        funTab.put("round", initFunction(new BigDecimalRound()));
        funTab.put("floor", initFunction(new BigDecimalFloor()));
        funTab.put("ceil", initFunction(new BigDecimalCeil()));
    }

    private PostfixMathCommandI initFunction(PostfixMathCommandI f) {
        ((MathContextAware) f).setMathContext(numberFactory.getMathContext());
        ((NumberFactoryAware<BigDecimal>) f).setNumberFactory(numberFactory);
        return f;
    }

}
