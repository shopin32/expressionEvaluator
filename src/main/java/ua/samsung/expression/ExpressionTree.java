package ua.samsung.expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import ua.samsung.model.expression.ExpressionItem;
import ua.samsung.model.expression.ExpressionItemType;
import ua.samsung.model.graph.BinaryNode;
import ua.samsung.model.graph.BinaryTree;
/**
 * Expression Tree to contain the initial string expression
 * @author petro.shopin
 *
 */
public class ExpressionTree extends BinaryTree<ExpressionItem>
{
	/**
	 * Map of available operators and how them to interpret
	 */
	private Map<String, BiFunction<Double, Double,Double>> _operatorsBehaviour = new HashMap<>();
	
	public ExpressionTree(BinaryNode<ExpressionItem> nodeValue) 
	{
		super(nodeValue);
		
		_operatorsBehaviour.put("*", (a,b) -> a*b);
		_operatorsBehaviour.put("+", (a,b) -> a+b);
		_operatorsBehaviour.put("/", (a,b) -> a/b);
		_operatorsBehaviour.put("-", (a,b) -> a-b);
	}
	
	public Double evaluate()
	{
		return evaluate(getRoot());
	}
	
	public Double evaluate(BinaryNode<ExpressionItem> item)
	{
		if(item.getNodeValue().getItemType().equals(ExpressionItemType.OPERATOR))
		{
			return _operatorsBehaviour.get(item.getNodeValue().getValueAsString()).apply(evaluate(item.getLeft()), evaluate(item.getRight()));
		}
		
		if(item.getNodeValue().getItemType().equals(ExpressionItemType.OPERAND))
		{
			return Double.parseDouble(item.getNodeValue().getValueAsString());
		}
		throw new IllegalArgumentException("the tree structure is not valid");
	}
	
	public String infix()
	{
		return infix(getRoot());
	}
	
	public String infix(BinaryNode<ExpressionItem> item)
	{
		String result = "";
		if(item == null) return result;
		if(item.getNodeValue().getItemType().equals(ExpressionItemType.OPERATOR))
		{
			result+="(";
		}
		result += infix(item.getLeft());
		result += item.getNodeValue().getValueAsString();
		result += infix(item.getRight());
		if(item.getNodeValue().getItemType().equals(ExpressionItemType.OPERATOR))
		{
			result+=")";
		}
		return result;
	}
	
	public boolean variableValue(String variableName, Double value)
	{
		return replace(getRoot(), item -> item.getValueAsString().equals(variableName), new ExpressionItem(value.toString(), ExpressionItemType.OPERAND));
	}
	
	public List<ExpressionItem> findVariables()
	{
		return find(ExpressionItemType.VARIABLE);
	}
	
	public List<ExpressionItem> find(ExpressionItemType type)
	{
		return find(getRoot(), item -> item.getItemType().equals(type));
	}

	@Override
	public String toString() {
		return infix();
	}
}
