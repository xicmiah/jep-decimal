/*****************************************************************************

 JEP 2.4.1, Extensions 1.1.1
 April 30 2007
 (c) Copyright 2007, Nathan Funk and Richard Morris
 See LICENSE-*.txt for license information.

 *****************************************************************************/

package org.nfunk.jep.type;

import org.nfunk.jep.ParseException;

/**
 * This interface can be implemented to create numbers of any object type.
 * By implementing this interface and calling the setNumberFactory() method of
 * the JEP class, the constants in an expression will be created with that
 * class.
 */
public interface NumberFactory<T> {

    /**
     * Creates a number object and initializes its value.
     *
     * @param value The initial value of the number as a string.
     */
    public T createNumber(String value) throws ParseException;

    /**
     * Creates a number object with given double value.
     */
    public T createNumber(double value) throws ParseException;

    /**
     * Create a number object with given int value
     */
    public T createNumber(int value) throws ParseException;

    /**
     * Create a number object with given short value
     */
    public T createNumber(short value) throws ParseException;

    /**
     * Create a number object with given float value
     */
    public T createNumber(float value) throws ParseException;

    /**
     * Create a number object with given boolean value
     */
    public T createNumber(boolean value) throws ParseException;

    /**
     * Creates a number object from a class implementing Number,
     */
    public T createNumber(Number value) throws ParseException;

    /**
     * Create a number object with given Complex value
     */
    public T createNumber(Complex value) throws ParseException;

    /**
     * Return an object representing ZERO the additive identity.
     */
    public T getZero();

    /**
     * Return an object representing ONE the multiplicative identity.
     */
    public T getOne();

    /**
     * Return an object representing ZERO-ONE.
     */
    public T getMinusOne();

    /**
     * Return an object representing ONE+ONE.
     */
    public T getTwo();
}
