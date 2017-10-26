package ua.samsung.expression;

import java.util.List;
import java.util.Map.Entry;
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
		
		
		//TODO
		return null;
	}
	
	public EvaluationGraph fillTheGraph(String[][] expressions)
	{
		EvaluationGraph graph = new EvaluationGraph();
		ExpressionTree[][] expressionTrees = new ExpressionTree[expressions.length][];
		
		for(int i = 0; i < expressions.length; i++)
		{
			int length = expressions[i].length;
			expressionTrees[i] = new ExpressionTree[length];
			
			for(int j = 0; j < length; j++)
			{
				String expression = expressions[i][j];
				ExpressionTree tree = (expressionTrees[i][j] == null) ? _parser.parse(expression): expressionTrees[i][j];
				expressionTrees[i][j] = tree;
				
				List<ExpressionItem> variables = tree.findVariables();
				
				for(ExpressionItem variable: variables)
				{
					Entry<Integer, Integer> indexes = _mapVariableToAnotherExpression.apply(variable.getValueAsString());
					ExpressionTree targetTree = expressionTrees[indexes.getKey()][indexes.getValue()];
					
					if(targetTree == null)
					{
						targetTree = _parser.parse(expressions[indexes.getKey()][indexes.getValue()]);
						expressionTrees[indexes.getKey()][indexes.getValue()] = targetTree;
					}
					graph.addEdge(tree, targetTree);
				}
			}
		}
		return graph;
	}
}
