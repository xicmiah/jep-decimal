package org.nfunk.jep.config;

import org.nfunk.jep.BDOperatorSetNonstrict;
import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.function.*;
import org.nfunk.jep.function.bdnonstrict.BDAbsNonstrict;
import org.nfunk.jep.function.bdnonstrict.BDCeilNonstrict;
import org.nfunk.jep.function.bdnonstrict.BDFloorNonstrict;
import org.nfunk.jep.function.bdnonstrict.BDRoundNonstrict;
import org.nfunk.jep.function.operator.AbstractAdd;
import org.nfunk.jep.function.operator.bdnonstrict.BDAddNonstrict;
import org.nfunk.jep.function.operator.bdnonstrict.BDModulusNonstrict;
import org.nfunk.jep.function.operator.bdnonstrict.BDPowerNonstrict;

import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BigDecimalNonstrictConfig extends AbstractBigDecimalConfig {

    public BigDecimalNonstrictConfig(MathContext context) {
        super(context);
        operatorSet = new BDOperatorSetNonstrict(numberFactory);
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
        funTab.put("pow", initFunction(new BDPowerNonstrict()));

        funTab.put("sqrt", new SquareRoot());
        funTab.put("abs", initFunction(new BDAbsNonstrict()));
        funTab.put("mod", initFunction(new BDModulusNonstrict()));
        funTab.put("sum", new Sum((AbstractAdd) initFunction(new BDAddNonstrict())));

        funTab.put("rand", new org.nfunk.jep.function.Random());

        funTab.put("if", new If());
        funTab.put("str", new Str());

        funTab.put("binom", new Binomial());

        funTab.put("round", initFunction(new BDRoundNonstrict()));
        funTab.put("floor", initFunction(new BDFloorNonstrict()));
        funTab.put("ceil", initFunction(new BDCeilNonstrict()));
    }
}
