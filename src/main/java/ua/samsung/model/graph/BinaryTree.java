package ua.samsung.model.graph;

public class BinaryTree<T> 
{
	private BinaryNode<T> _root;
	
	public BinaryTree(BinaryNode<T> root)
	{
		_root = root;
	}
	
	public BinaryNode<T> getRoot()
	{
		return _root;
	}
}
