package ua.samsung.model.graph;

/**
 * 
 * @author petro.shopin
 *
 * @param <T>
 */
public class BinaryNode<T> 
{
	private T _nodeValue;
	
	private BinaryNode<T>  _left, _right;
	
	public BinaryNode(T nodeValue)
	{
		_nodeValue = nodeValue;
	}

	public BinaryNode<T> getLeft() {
		return _left;
	}

	public void setLeft(BinaryNode<T> left) {
		_left = left;
	}

	public BinaryNode<T> getRight() {
		return _right;
	}

	public void setRight(BinaryNode<T> right) {
		_right = right;
	}

	public T getNodeValue() {
		return _nodeValue;
	}
}
