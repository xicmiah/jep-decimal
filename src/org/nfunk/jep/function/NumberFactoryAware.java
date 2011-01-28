package org.nfunk.jep.function;

import org.nfunk.jep.type.NumberFactory;

/**
 * @author DPavlov
 */
public interface NumberFactoryAware<T> {

    void setNumberFactory(NumberFactory<T> factory);

}
