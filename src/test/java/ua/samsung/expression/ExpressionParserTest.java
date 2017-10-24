package ua.samsung.expression;

import static org.junit.Assert.*;

import org.junit.Test;


public class ExpressionParserTest {

	@Test
	public void testInfixToPostfix() 
	{
		ExpressionParser parser = new ExpressionParser();
		
		String result = parser.infixToPostfix("a+b*c");
		
		assertEquals("Incorrect transformation","a b c * +", result);
	}

}
