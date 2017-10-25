package ua.samsung.expression;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import ua.samsung.model.expression.ExpressionItem;
import ua.samsung.model.expression.ExpressionItemType;


public class ExpressionParserTest {

	@Test
	public void testInfixToPostfix() 
	{
		//Arrange
		ExpressionParser parser = new ExpressionParser();
		Double expected = (double)25*50/46+7/1-2*2-76/1;
		
		//Act
		Double result = parser.parse("25*50/46+7/1-2*2-76/1").evaluate();
		
		//Assert
		assertEquals("Incorrect calculated",expected, result, 0.00001d);
	}
	
	
	@Test
	public void testInfixToPostfix2() 
	{
		//Arrange
		ExpressionParser parser = new ExpressionParser();
		Double expected = (double)26-1-1-2-3-4*5;
		
		//Act
		Double result = parser.parse("26-1-1-2-3-4*5").evaluate();
		
		//Assert
		assertEquals("Incorrect calculated",expected, result, 0.00001d);
	}
	
	@Test
	public void testLookingForVariables()
	{
		//Arrange
		ExpressionParser parser = new ExpressionParser();
		
		//Act
		ExpressionTree tree = parser.parse("26-1-1*10/2-x");
		List<ExpressionItem> vars = tree.find(ExpressionItemType.VARIABLE);
		
		//Assert
		assertEquals(1, vars.size());
	}
}
