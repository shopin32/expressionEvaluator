package ua.samsung.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;

import ua.samsung.model.expression.EvaluationGraph;
import ua.samsung.model.expression.ExpressionItem;

public class EvaluationEngine 
{
	private ExpressionParser _parser;
	
	private Function<String, Entry<Integer, Integer>> _mapVariableToAnotherExpression;
	
	public EvaluationEngine(ExpressionParser parser, Function<String, Entry<Integer, Integer>> mapVariableToAnotherExpression)
	{
		_parser = parser;
		_mapVariableToAnotherExpression = mapVariableToAnotherExpression;
	}
	
	public String[][] evaluate(String[][] expressions)
	{
		ExpressionTree[][] expressionTrees = parseExpressions(expressions);
		EvaluationGraph graph = fillTheGraph(expressionTrees);
		
		
		
		//TODO
		return null;
	}
	
	public String[][] compute(EvaluationGraph graph, ExpressionTree[][] expressionTrees)
	{
		Map<ExpressionTree, Boolean> expressionEvaluationStatus = graph.verticesEvaluationStatus();
		
		Map<ExpressionTree, Double> evaluatedResults = new HashMap<>();
		
		for(ExpressionTree vertex : graph.getVertices())
			
			
	}
	
	
	public static Map<ExpressionTree, Double> fill(EvaluationGraph graph, ExpressionTree root, Map<ExpressionTree, Boolean> expressionEvaluationStatus)
	{
		List<ExpressionItem> a = graph.getEdges(root);
	}
	
	public EvaluationGraph fillTheGraph(ExpressionTree[][] expressionTrees)
	{
		EvaluationGraph graph = new EvaluationGraph();
		
		for(int i = 0; i < expressionTrees.length; i++)
		{
			int length = expressionTrees[i].length;
			for(int j = 0; j < length; j++)
			{
				ExpressionTree tree = expressionTrees[i][j];
				graph.addVertex(tree);
				
				List<ExpressionItem> variables = tree.findVariables();
				
				for(ExpressionItem variable: variables)
				{
					Entry<Integer, Integer> indexes = _mapVariableToAnotherExpression.apply(variable.getValueAsString());
					ExpressionTree targetTree = expressionTrees[indexes.getKey()][indexes.getValue()];
					
					graph.addEdge(tree, targetTree, variable.getValueAsString());
				}
			}
		}
		return graph;
	}
	
	public ExpressionTree[][] parseExpressions(String[][] expressions)
	{
		ExpressionTree[][] expressionTrees = new ExpressionTree[expressions.length][];
		
		for(int i = 0; i < expressions.length; i++)
		{
			expressionTrees[i] = new ExpressionTree[expressions[i].length];
			for(int j = 0; j < expressions.length; j++)
			{
				expressionTrees[i][j] = _parser.parse(expressions[i][j]);
			}
		}
		return expressionTrees;
	}
	
}
