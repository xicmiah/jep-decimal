package org.nfunk.jep.validation;

import java.util.Collection;
import java.util.HashSet;

/**
 * Immutable scope, stores list of variables
 */
public class Scope {
	private static final Scope EMPTY = new Scope();

	private Collection<String> variables = new HashSet<String>();

	private Scope() {
	}

	private Scope(Scope other) {
		this.variables = new HashSet<String>(other.variables);
	}

	/**
	 * Get empty scope
	 * @return empty scope
	 */
	public static Scope empty() {
		return EMPTY;
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
	 * Returns scope, which contains supplied variable
	 * @param variable variable to add to scope
	 * @return extended scope
	 */
	public Scope with(String variable) {
		if (variables.contains(variable)) {
			return this;
		} else {
			Scope other = new Scope(this);
			other.variables.add(variable);
			return other;
		}
	}

	/**
	 * Scopes are equal, if they contain same variables
	 * @param o other scope
	 * @return true, if this scope is equivalent to other
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Scope scope = (Scope) o;

		return variables.equals(scope.variables);

	}

	@Override
	public int hashCode() {
		return variables.hashCode();
	}

	@Override
	public String toString() {
		return "Scope{" + variables + '}';
	}
}
