package org.nfunk.jep.config;

import org.nfunk.jep.BigDecimalOperatorSet;
import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.function.ArcCosine;
import org.nfunk.jep.function.ArcCosineH;
import org.nfunk.jep.function.ArcSine;
import org.nfunk.jep.function.ArcSineH;
import org.nfunk.jep.function.ArcTanH;
import org.nfunk.jep.function.ArcTangent;
import org.nfunk.jep.function.ArcTangent2;
import org.nfunk.jep.function.Binomial;
import org.nfunk.jep.function.Cosine;
import org.nfunk.jep.function.CosineH;
import org.nfunk.jep.function.Exp;
import org.nfunk.jep.function.If;
import org.nfunk.jep.function.IsDefined;
import org.nfunk.jep.function.Logarithm;
import org.nfunk.jep.function.NaturalLogarithm;
import org.nfunk.jep.function.Sine;
import org.nfunk.jep.function.SineH;
import org.nfunk.jep.function.SquareRoot;
import org.nfunk.jep.function.Str;
import org.nfunk.jep.function.Sum;
import org.nfunk.jep.function.TanH;
import org.nfunk.jep.function.Tangent;
import org.nfunk.jep.function.bigdecimal.BigDecimalAbs;
import org.nfunk.jep.function.bigdecimal.BigDecimalCeil;
import org.nfunk.jep.function.bigdecimal.BigDecimalFloor;
import org.nfunk.jep.function.bigdecimal.BigDecimalRound;
import org.nfunk.jep.function.operator.AbstractAdd;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalAdd;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalModulus;
import org.nfunk.jep.function.operator.bigdecimal.BigDecimalPower;

import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalConfig extends AbstractBigDecimalConfig {

    public BigDecimalConfig(MathContext context) {
        super(context);
        this.operatorSet = new BigDecimalOperatorSet(numberFactory);
        addStandardFunctions();
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

	    funTab.put("isDefined", new IsDefined());
    }

}
