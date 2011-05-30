package org.nfunk.jep.validation;

import org.nfunk.jep.SymbolTable;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Immutable scope, stores list of variables
 */
public class Scope {
	private static final Scope EMPTY = new Scope(Collections.<String>emptySet(), true);

	/**
	 * Variables in this scope
	 */
	private final Collection<String> variables;

	/**
	 * Open/closed status.
	 * Closed scopes cannot be extended
	 */
	private final boolean open;

	private Scope(Collection<String> variables, boolean open) {
		this.variables = new HashSet<String>(variables);
		this.open = open;
	}

	private Scope(Scope other) {
		this(other.variables, other.open);
	}


	/**
	 * Get empty open scope
	 * @return empty scope
	 */
	public static Scope empty() {
		return EMPTY;
	}

	/**
	 * Init scope from collection of variable names
	 * @param variables collection of variable names to include in scope
	 * @return open scope which contains supplied variables
	 */
	public static Scope fromCollection(Collection<String> variables) {
		return new Scope(variables, true);
	}

	/**
	 * Initializes scope from symbol table
	 * @param symbolTable
	 * @return open scope which contains variables from supplied table
	 */
	public static Scope fromSymbolTable(SymbolTable symbolTable) {
		return fromCollection(symbolTable.getVarNames());
	}

	/**
	 * Check if scope contains a variable
	 * @param variable variable name to check
	 * @return true if scope contains variable, false otherwise
	 */
	public boolean contains(String variable) {
		return variables.contains(variable);
	}

	/**
	 * Return if variables can be added to this scope
	 * @return true, if this scope can be extended via with() method, false otherwise
	 */
	public boolean isOpen() {
		return open;
	}

	/**
	 * Returns scope, which contains supplied variable.
	 * Operation fails if this scope is closed and doesn't contain the variable
	 * @param variable variable to add to scope
	 * @return extended scope
	 * @throws ValidationException if scope is closed and doesn't contain variable
	 */
	public Scope with(String variable) throws ValidationException {
		if (variables.contains(variable)) {
			return this;
		}
		
		if (!open) throw new ValidationException("Scope is closed");
			
		Scope other = new Scope(this);
		other.variables.add(variable);
		return other;
	}

	/**
	 * Closes this scope
	 * @return closed scope, which contains variables in this scope
	 */
	public Scope closed() {
		if (!open) return this;
		else return new Scope(variables, false);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Scope scope = (Scope) o;

		return open == scope.open && variables.equals(scope.variables);

	}

	@Override
	public int hashCode() {
		int result = variables.hashCode();
		result = 31 * result + (open ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Scope{" +
				variables +
				", open=" + open +
				'}';
	}
}
