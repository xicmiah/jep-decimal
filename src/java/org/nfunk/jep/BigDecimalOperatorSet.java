package org.nfunk.jep;

import org.nfunk.jep.function.MathContextAware;
import org.nfunk.jep.function.NumberFactoryAware;
import org.nfunk.jep.function.PostfixMathCommand;
import org.nfunk.jep.function.operator.*;
import org.nfunk.jep.function.operator.bigdecimal.*;
import org.nfunk.jep.type.BigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Set of operators that uses BigDecimal numbers for perfoming actions
 *
 * @author DPavlov
 */
public class BigDecimalOperatorSet extends OperatorSet{

    public BigDecimalOperatorSet(BigDecimalNumberFactory numberFactory){
        this(numberFactory, numberFactory.getMathContext());
    }

    public BigDecimalOperatorSet(NumberFactory<BigDecimal> numberFactory, MathContext mathContext){
        initOperators(numberFactory, mathContext);
    }

    protected void initOperators(NumberFactory<BigDecimal> numberFactory, MathContext mathContext) {
        this.OP_ADD.setPFMC(initOp(new BigDecimalAdd(), numberFactory, mathContext));
        this.OP_SUBTRACT.setPFMC(initOp(new BigDecimalSubtract(), numberFactory, mathContext));
        this.OP_UMINUS.setPFMC(initOp(new BigDecimalUMinus(), numberFactory, mathContext));
        this.OP_MULTIPLY.setPFMC(initOp(new BigDecimalMultiply(), numberFactory, mathContext));
        this.OP_DIVIDE.setPFMC(initOp(new BigDecimalDivide(), numberFactory, mathContext));
        this.OP_MOD.setPFMC(initOp(new BigDecimalModulus(), numberFactory, mathContext));
        this.OP_POWER.setPFMC(initOp(new BigDecimalPower(), numberFactory, mathContext));
        this.OP_DOT.setPFMC(new Dot(
                (AbstractAdd) initOp(new BigDecimalAdd(), numberFactory, mathContext),
                (AbstractMultiply) initOp(new BigDecimalMultiply(), numberFactory, mathContext)
        ));
        this.OP_CROSS.setPFMC(new Cross(
                (AbstractSubtract) initOp(new BigDecimalSubtract(), numberFactory, mathContext),
                (AbstractMultiply) initOp(new BigDecimalMultiply(), numberFactory, mathContext)
        ));
    }

    protected PostfixMathCommand initOp(PostfixMathCommand op, NumberFactory<BigDecimal> numberFactory, MathContext mathContext){
        ((NumberFactoryAware<BigDecimal>)op).setNumberFactory(numberFactory);
        ((MathContextAware)op).setMathContext(mathContext);
        return op;
    }

}
