package org.nfunk.jep;

import org.nfunk.jep.function.operator.*;
import org.nfunk.jep.function.operator.bdnonstrict.*;
import org.nfunk.jep.type.BigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author DPavlov
 */
public class BDOperatorSetNonstrict extends BigDecimalOperatorSet {

    public BDOperatorSetNonstrict(BigDecimalNumberFactory numberFactory) {
        super(numberFactory);
    }

    public BDOperatorSetNonstrict(NumberFactory<BigDecimal> numberFactory, MathContext mathContext) {
        super(numberFactory, mathContext);
    }

    @Override
    protected void initOperators(NumberFactory<BigDecimal> numberFactory, MathContext mathContext) {
        this.OP_ADD.setPFMC(initOp(new BDAddNonstrict(), numberFactory, mathContext));
        this.OP_SUBTRACT.setPFMC(initOp(new BDSubtractNonstrict(), numberFactory, mathContext));
        this.OP_UMINUS.setPFMC(initOp(new BDUMinusNonstrict(), numberFactory, mathContext));
        this.OP_MULTIPLY.setPFMC(initOp(new BDMultiplyNonstrict(), numberFactory, mathContext));
        this.OP_DIVIDE.setPFMC(initOp(new BDDivideNonstrict(), numberFactory, mathContext));
        this.OP_MOD.setPFMC(initOp(new BDModulusNonstrict(), numberFactory, mathContext));
        this.OP_POWER.setPFMC(initOp(new BDPowerNonstrict(), numberFactory, mathContext));
        this.OP_DOT.setPFMC(new Dot(
                (AbstractAdd) initOp(new BDAddNonstrict(), numberFactory, mathContext),
                (AbstractMultiply) initOp(new BDMultiplyNonstrict(), numberFactory, mathContext)
        ));
        this.OP_CROSS.setPFMC(new Cross(
                (AbstractSubtract) initOp(new BDSubtractNonstrict(), numberFactory, mathContext),
                (AbstractMultiply) initOp(new BDMultiplyNonstrict(), numberFactory, mathContext)
        ));
    }
}
