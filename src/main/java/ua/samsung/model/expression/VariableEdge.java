package ua.samsung.model.expression;

import ua.samsung.expression.ExpressionTree;
import ua.samsung.model.graph.Edge;

public class VariableEdge extends Edge<ExpressionTree>
{
	private String _variableName;

	public VariableEdge(ExpressionTree source, ExpressionTree target, String variableName) 
	{
		super(source, target);
	}
	
	public String getVariableName()
	{
		return _variableName;
	}

}
