/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/
package org.nfunk.jep;

import org.nfunk.jep.function.PostfixMathCommandI;

import java.util.*;

/*
 * A Hashtable which holds a list of functions.
 */

public class FunctionTable {
    private static final long serialVersionUID = -1192898221311853572L;

    private Map<String, PostfixMathCommandI> map = new HashMap<String,PostfixMathCommandI>();

    public FunctionTable() {

    }

    /**
     *
     * @param s
     * @param pfmc
     * @return
     */
    public PostfixMathCommandI put(String s, PostfixMathCommandI pfmc) {
        return map.put(s, pfmc);
    }

    /**
     * 
     * @param s
     * @return
     */
    public PostfixMathCommandI get(String s) {
        return map.get(s);
    }

    public PostfixMathCommandI remove(String name){
        return map.remove(name);
    }

    public boolean contains(String name){
        return map.containsKey(name);
    }

    public Set<String> functionNames(){
        return map.keySet();
    }

}
