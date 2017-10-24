package ua.samsung.expression;

import ua.samsung.model.expression.ExpressionItem;
import ua.samsung.model.expression.ExpressionItemType;
import ua.samsung.model.graph.BinaryNode;
import ua.samsung.model.graph.BinaryTree;

public class ExpressionTree extends BinaryTree<ExpressionItem>
{
	public ExpressionTree(BinaryNode<ExpressionItem> nodeValue) 
	{
		super(nodeValue);
	}
	
	
	public boolean any(ExpressionItemType type)
	{
		return first(getRoot(), type) != null;
	}
	
	public BinaryNode<ExpressionItem> first(BinaryNode<ExpressionItem> node, ExpressionItemType type)
	{
		if(node == null) return null;
		if(node.getNodeValue().getItemType().equals(type)) return node;
		BinaryNode<ExpressionItem> leftFind = first(node.getLeft(), type);
		if(leftFind !=null) return leftFind; 
		BinaryNode<ExpressionItem> rightFind = first(node.getRight(), type);
		return rightFind;
	}
}
