package ua.samsung.model.expression;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import ua.samsung.expression.ExpressionTree;
import ua.samsung.model.graph.Graph;

public class EvaluationGraph extends Graph<ExpressionTree, VariableEdge> 
{	
	public Map<ExpressionTree, Boolean> verticesEvaluationStatus()
	{
		Map<ExpressionTree, Boolean> visitedVertexes = new HashMap<>();
		
		for(ExpressionTree vertex: getVertices())
		{
			Stack<ExpressionTree> path = new Stack<ExpressionTree>();
			path.add(vertex);
			countElementsThatCanNotBeEvaluated(path, visitedVertexes);
		}
		return visitedVertexes;
	}
	
	public void countElementsThatCanNotBeEvaluated(
			Stack<ExpressionTree> path, 
			Map<ExpressionTree, Boolean> visitedToEval)
	{
		if(visitedToEval.containsKey(path.peek()))
		{
			ExpressionTree tree = path.pop();
			if(!path.isEmpty() && !visitedToEval.get(tree))
			{
				for(ExpressionTree item: path)
				{
					visitedToEval.put(item, false);
				}
			}
			return;
		}
		
		visitedToEval.put(path.peek(),true);
		Set<ExpressionTree> children = successors(path.peek());
		for(ExpressionTree child: children)
		{
			if(path.contains(child))
			{
				for(ExpressionTree item: path)
				{
					visitedToEval.put(item, false);
				}
				continue;
			}
			
			path.push(child);
			countElementsThatCanNotBeEvaluated(path, visitedToEval);
		}
		path.pop();
	}
	
	
	
	public void addEdge(ExpressionTree source, ExpressionTree target, String name)
	{
		addVertex(source);
		addVertex(target);
		
		addEdge(source, new VariableEdge(source, target, name));
	}
}
