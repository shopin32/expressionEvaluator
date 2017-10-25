package ua.samsung.model.expression;

public class ExpressionItem 
{
	private String _itemValue;
	
	private ExpressionItemType _type;
	
	public ExpressionItem(String itemValue, ExpressionItemType type)
	{
		_itemValue = itemValue;
		_type = type;
	}
	
	public String getValueAsString()
	{
		return _itemValue;
	};
	
	public ExpressionItemType getItemType()
	{
		return _type;
	}

	@Override
	public String toString() 
	{
		return "ExpressionItem [_itemValue=" + _itemValue + ", _type=" + _type + "]";
	}
	
	
}
