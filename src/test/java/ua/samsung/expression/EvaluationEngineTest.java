package ua.samsung.expression;

import static org.junit.Assert.*;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import ua.samsung.model.expression.EvaluationGraph;
import ua.samsung.model.expression.VariableEdge;
import ua.samsung.model.graph.Graph;

public class EvaluationEngineTest {

	@Test
	public void testFillTheGraph() 
	{
		//Arrange
		String[][] expressions = 
			{
				{"12+[0,1]", "[0,2]", "[1,0]"}, 
				{"3-[0,1]", "1", "2"}
			};
		String variableParsing = "[\\[](\\d+),(\\d+)[\\]]";
		ExpressionParser parser = new ExpressionParser(variableParsing);
		EvaluationEngine engine = new EvaluationEngine(parser, EvaluationEngineTest::mapToIndices);
		
		//Act
		EvaluationGraph graph = engine.fillTheGraph(expressions);
		Map<ExpressionTree, Boolean> res = graph.evaluate(); 
		String s = String.join(",",graph.getEdges().stream().map(VariableEdge::getVariableName).toArray(String[]::new));
		//Assert
		assertEquals(4, graph.getVertices().size());
		assertEquals(2, graph.getEdges().size());
	}
	
	public static Entry<Integer, Integer> mapToIndices(String s)
	{
		String variableParsing = "[\\[](\\d+),(\\d+)[\\]]";
		
		Pattern pattern = Pattern.compile(variableParsing);
		
		Matcher matcher = pattern.matcher(s);
		matcher.find();
		
		return new AbstractMap.SimpleEntry<Integer, Integer>(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
	}

}
