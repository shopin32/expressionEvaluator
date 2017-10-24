package ua.samsung.expression;

import java.util.*;
import java.util.Map.Entry;

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
		
			
		
				
		//TODO
		return null;
	}
	
	public String infixToPostfix(String expression)
	{
		char[] expressionChars = expression.toCharArray();
		
		Stack<Character> stack = new Stack<>();
		
		StringBuilder output = new StringBuilder();
		
		Character lastAppendeChar = null;
		for(int charIndex = 0; charIndex < expressionChars.length; charIndex++)
		{
			char currentChar = expressionChars[charIndex];
			if(!_operators.contains(currentChar))
			{
				output.append(currentChar);
				lastAppendeChar = currentChar;
			}
			else
			{
				output.append(" ");
				if(!stack.isEmpty() && !isLowerPrecedence(currentChar, stack.peek()))
				{
					stack.push(currentChar);
				}
				else
				{
					while(!stack.isEmpty() && isLowerPrecedence(currentChar, stack.peek()))
					{
						output.append(stack.pop()).append(" ");
						lastAppendeChar = ' ';
					}
					stack.push(currentChar);
				}
			}
		}
			
		while (!stack.isEmpty()) 
		{
			Character val = stack.pop();
			if(_operators.contains(val) && lastAppendeChar != ' ') output.append(" ");
			output.append(val);
	    }
	    return output.toString().trim();
	}
	
	public boolean isLowerPrecedence(char operator1, char operator2)
	{
		switch(operator1)
		{
			case '+':
			case '-':
				return operator2 == '*' || operator2 == '/';
			default: 
				return false;
		}
	}
	
	
	
	public List<Entry<Character, Integer>> findAllOperators(char[] expressionChars)
	{
		int cursor = 0;
		List<Entry<Character, Integer>> result = new ArrayList<>();
		
		while(true)
		{
			int operatorIndex = findFirstOperatorOccurrance(expressionChars, cursor);
			if( operatorIndex < 0) break;
			
			result.add(new AbstractMap.SimpleEntry<Character, Integer>(expressionChars[cursor], operatorIndex));
			cursor = operatorIndex+1;
		}
		
		return result;
	}
	
		
	public int findFirstOperatorOccurrance(char[] expressionChars, int startFrom)
	{
		if(startFrom >= expressionChars.length) return -1;
		for(int charIndex = startFrom; charIndex< expressionChars.length; charIndex++)
		{
			if(_operators.contains(expressionChars[charIndex])) return charIndex;
		}
		return -1;
	}
	
	
}
