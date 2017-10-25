package ua.samsung.expression;

import java.util.*;
import java.util.Map.Entry;

import ua.samsung.model.expression.ExpressionItem;
import ua.samsung.model.expression.ExpressionItemType;
import ua.samsung.model.graph.BinaryNode;

public class ExpressionParser 
{
	private Set<Character> _operators;
	
	public ExpressionParser(Character... operators)
	{
		_operators = new HashSet<>(Arrays.asList(operators));
	}
	
	public ExpressionParser()
	{
		this('+','-','/','*');
	}
	
	public ExpressionTree parse(String expression)
	{
		List<ExpressionItem> postfixExpression = infixToPostfix(expression);
			
		return new ExpressionTree(convertExpressionIntoTree(postfixExpression));
	}
	
	public BinaryNode<ExpressionItem> convertExpressionIntoTree(List<ExpressionItem> postfix)
	{
		Stack<BinaryNode<ExpressionItem>> stack = new Stack<>();
		
		for(ExpressionItem item: postfix)
		{
			
			if(!item.getItemType().equals(ExpressionItemType.OPERATOR))
			{
				stack.push(new BinaryNode<ExpressionItem>(item));
			}
			else
			{
				BinaryNode<ExpressionItem> root = new BinaryNode<ExpressionItem>(item);
				root.setRight(stack.pop());
				root.setLeft(stack.pop());
				stack.push(root);
			}
		}
		
		return stack.pop();
	}
	
	
	public List<ExpressionItem> infixToPostfix(String expression)
	{
		List<ExpressionItem> expressionItems = parseToExpressionItems(expression);
		
		Stack<ExpressionItem> stack = new Stack<>();
		
		List<ExpressionItem> postfixCollection = new ArrayList<>();
		
		for(int i = 0; i < expressionItems.size(); i++)
		{
			ExpressionItem currentItem = expressionItems.get(i);
			if(!currentItem.getItemType().equals(ExpressionItemType.OPERATOR))
			{
				postfixCollection.add(currentItem);
			}
			else
			{
				if(!stack.isEmpty() && !isLowerPrecedence(currentItem, stack.peek()))
				{
					stack.push(currentItem);
				}
				else
				{
					while(!stack.isEmpty() && isLowerPrecedence(currentItem, stack.peek()))
					{
						postfixCollection.add(stack.pop());
					}
					stack.push(currentItem);
				}
			}
		}
			
		while (!stack.isEmpty()) 
		{
			postfixCollection.add(stack.pop());
	    }
	    return postfixCollection;
	}
	
	public List<ExpressionItem> parseToExpressionItems(String expression)
	{
		String[] expressionItemsStrings = expression.split("[\\+\\-\\*\\/]");
		List<ExpressionItem> result = new ArrayList<>();
		
		int index = 0;
		for(int i =0; i < expressionItemsStrings.length; i++)
		{
			String expressionString = expressionItemsStrings[i];
			result.add(toExpressionItem(expressionString));
			if(i < expressionItemsStrings.length-1)
			{
				int start = index+ expressionString.length();
				result.add(toExpressionItem(expression.substring(start, start+1)));
			}
			index += expressionString.length() +1;
		}
		return result;
	}
	
	
	public ExpressionItem toExpressionItem(String expressionString)
	{
		ExpressionItemType type = ExpressionItemType.OPERAND;
		
		if(expressionString.length() == 1 && _operators.contains(expressionString.charAt(0)))
		{
			type = ExpressionItemType.OPERATOR;
		} 
		else if(expressionString.matches("[a-zA-Z]+"))
		{
			type = ExpressionItemType.VARIABLE;
		}
		
		return new ExpressionItem(expressionString, type);
	}
	
	
	public boolean isLowerPrecedence(ExpressionItem operator1, ExpressionItem operator2)
	{
		switch(operator1.getValueAsString())
		{
			case "+":
				return operator2.getValueAsString().equals("-") || operator2.getValueAsString().equals("*") || operator2.getValueAsString().equals("/");
			case "-":
				return operator2.getValueAsString().equals("-") || operator2.getValueAsString().equals("*") || operator2.getValueAsString().equals("/");
			default: 
				return false;
		}
	}
}
