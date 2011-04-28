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

	@Test
	public void testScopedDeclarationsPass() throws Exception {
		Node node = jep.parse("a = 5; a*2");
		validate(node);
	}

	@Test(expected = ValidationException.class)
	public void testScopedDeclarationsFail() throws Exception {
		Node node = jep.parse("if (1) then { a = 3 } else {4}; a*3");
		validate(node);
	}

	@Test
	public void testComplexAssigns() throws Exception {
		Node node = jep.parse("a = b = 5; a*2; b*3");
		validate(node);
	}

	@Test(expected = ValidationException.class)
	public void testComplexAssignsFail() throws Exception {
		Node node = jep.parse("a = b = 5; if (-1) then {c = 5}; a*2; b*3; 2c");
		validate(node);
	}
}
