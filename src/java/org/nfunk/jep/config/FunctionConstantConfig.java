package org.nfunk.jep.config;

import org.nfunk.jep.FunctionTable;

import java.util.Map;

/**
 * @author DPavlov
 */
public interface FunctionConstantConfig {

    public FunctionTable getFunctions();

    public Map<String,Object> getConstants();

}
