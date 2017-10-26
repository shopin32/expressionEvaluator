package ua.samsung.model.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
	
	public List<T> find(BinaryNode<T> node, Predicate<T> isSearching)
	{
		List<T> result = new ArrayList<>();
		if(node == null) return result;
		if(isSearching.test(node.getNodeValue())) result.add(node.getNodeValue());
		
		result.addAll(find(node.getLeft(),isSearching));
		result.addAll(find(node.getRight(),isSearching));
		return result;
	}
	
	public boolean replace(BinaryNode<T> node, Predicate<T> isSearching, T updateValue)
	{
		if(node == null) return false;
		if(isSearching.test(node.getNodeValue()))
		{
			node.setNodeValue(updateValue);
			return true;
		}
		if(replace(node.getLeft(), isSearching, updateValue)) return true;
		
		return replace(node.getRight(), isSearching, updateValue);
	}
}
