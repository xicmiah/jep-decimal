package org.nfunk.jep;

import org.junit.Before;
import org.junit.Test;
import org.nfunk.jep.config.BigDecimalConfig;
import org.nfunk.jep.config.ConfigurationBuilder;
import org.nfunk.jep.config.JepConfiguration;
import org.nfunk.jep.type.LocalizedBigDecimalNumberFactory;
import org.nfunk.jep.type.NumberFactory;
import org.nfunk.jep.validation.Scope;
import org.nfunk.jep.validation.ValidationException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Locale;

public class ScopeAnalysisTest {
	JEP jep;

	@Before
	public void setUp() throws Exception {
		NumberFactory<BigDecimal> numberFactory = new LocalizedBigDecimalNumberFactory(MathContext.DECIMAL64, new Locale("RU", "ru"));
		JepConfiguration configuration = new ConfigurationBuilder()
				.initWith(new BigDecimalConfig(MathContext.DECIMAL64))
				.setVariableFactory(new VariableFactory())
				.setUseImplicitMultiplication(true)
				.setNumberFactory(numberFactory)
				.setAllowAssignment(true)
				.setAllowUndeclaredVariables(true)
				.createConfig();
		jep = new JEP(configuration);
	}


	private void validate(Node node) throws ParseException {
		node.jjtAccept(new AnalyserVisitor(jep), Scope.empty());
	}
	private void validateStrict(Node node) throws ParseException {
		node.jjtAccept(new StrictAnalyserVisitor(jep), Scope.empty());
	}


	/**
	 * Test that top-level assigns are allowed
	 * @throws Exception
	 */
	@Test
	public void testPlainAssigns() throws Exception {
		Node node = jep.parse("a = b = 5; a*2; b*3");
		validate(node);
		validateStrict(node);
	}

	/**
	 * Test that inner assigns are allowed, if variable was declared earlier
	 * @throws Exception
	 */
	@Test
	public void testInnerAssigns() throws Exception {
		Node node = jep.parse("a = b = 5; if (a > 0) then { a = -1; } else { b = 2; } a*b");
		validate(node);
		validateStrict(node);
	}

	/**
	 * Test that AnalyserVisitor allows inner declarations if variable is not used outside
	 * @throws Exception
	 */
	@Test
	public void testUnusedVariables() throws Exception {
		Node node = jep.parse("if (1) then { a = 1; }; 2*2");
		validate(node);
	}


	/**
	 * Test that AnalyserVisitor forbids usage of inner variables outside
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testComplexAssignsFail() throws Exception {
		Node node = jep.parse("a = b = 5; if (-1) then {c = 5;}; a*2; b*3; 2c");
		validate(node);
	}

	/**
	 * Test that StrictAnalyserVisitor forbids inner declarations
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testStrictValidation() throws Exception {
		Node node = jep.parse("if (1) then { a = 1; }; 2*2");
		validateStrict(node);
	}

	/**
	 * Test that StrictAnalyserVisitor forbids usage of inner variables outside
	 * @throws Exception
	 */
	@Test(expected = ValidationException.class)
	public void testStrictComplexAssignsFail() throws Exception {
		Node node = jep.parse("a = b = 5; if (-1) then {c = 5;}; a*2; b*3; 2c");
		validateStrict(node);
	}
}
