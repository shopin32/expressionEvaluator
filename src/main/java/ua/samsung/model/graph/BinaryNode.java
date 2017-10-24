package ua.samsung.model.graph;

/**
 * 
 * @author petro.shopin
 *
 * @param <T>
 */
public class BinaryNode<T, P extends BinaryNode> 
{
	private T _nodeValue;
	
	private P  _left, _right;
	
	public BinaryNode(T nodeValue)
	{
		_nodeValue = nodeValue;
	}

	public P getLeft() {
		return _left;
	}

	public void setLeft(P left) {
		_left = left;
	}

	public P getRight() {
		return _right;
	}

	public void setRight(P right) {
		_right = right;
	}

	public T getNodeValue() {
		return _nodeValue;
	}
}
