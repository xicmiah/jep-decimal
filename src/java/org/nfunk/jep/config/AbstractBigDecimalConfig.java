package org.nfunk.jep.config;

import org.nfunk.jep.FunctionTable;
import org.nfunk.jep.OperatorSet;
import org.nfunk.jep.function.MathContextAware;
import org.nfunk.jep.function.NumberFactoryAware;
import org.nfunk.jep.function.PostfixMathCommandI;
import org.nfunk.jep.type.BigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

/**
 * @author DPavlov
 */
public abstract class AbstractBigDecimalConfig implements ExtendedConfig {

    protected FunctionTable funTab;
    protected Map<String, Object> constants;
    protected BigDecimalNumberFactory numberFactory;
    protected OperatorSet operatorSet;

    public AbstractBigDecimalConfig(MathContext context) {
        this.numberFactory = new BigDecimalNumberFactory(context);
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

    protected void initConstants() {
        constants = new HashMap<String, Object>();
        constants.put("e", Math.E);
        constants.put("pi", Math.PI);
    }

    @SuppressWarnings({"unchecked"})
    protected PostfixMathCommandI initFunction(PostfixMathCommandI f) {
        ((MathContextAware) f).setMathContext(numberFactory.getMathContext());
        ((NumberFactoryAware<BigDecimal>) f).setNumberFactory(numberFactory);
        return f;
    }

}
