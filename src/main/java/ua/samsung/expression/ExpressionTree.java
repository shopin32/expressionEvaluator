package ua.samsung.expression;

import ua.samsung.model.expression.ExpressionItem;
import ua.samsung.model.expression.ExpressionItemType;
import ua.samsung.model.graph.BinaryNode;

public class ExpressionTree extends BinaryNode<ExpressionItem, ExpressionTree>
{
	public ExpressionTree(ExpressionItem nodeValue) 
	{
		super(nodeValue);
	}
	
	
	public boolean any(ExpressionItemType type)
	{
		if(this.getNodeValue().getItemType().equals(type)) return true;
		if(this.getLeft()!= null && this.getLeft().any(type)) return true;
		if(this.getRight()!= null && this.getRight().any(type)) return true;
		return false;
	}
}
