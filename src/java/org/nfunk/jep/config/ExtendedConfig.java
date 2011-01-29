package org.nfunk.jep.config;

import org.nfunk.jep.OperatorSet;
import org.nfunk.jep.config.FunctionConstantConfig;
import org.nfunk.jep.type.NumberFactory;

/**
 * @author DPavlov
 */
public interface ExtendedConfig extends FunctionConstantConfig {

    OperatorSet getOperatorSet();

    NumberFactory getNumberFactory();

}
