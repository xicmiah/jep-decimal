package org.nfunk.jep.function.operator.bigdecimal;

import java.math.MathContext;

/**
 * Interface for injecting math context
 *
 * @author DPavlov
 */
public interface MathContextAware {

    void setMathContext(MathContext context);

    MathContext getMathContext();

}
