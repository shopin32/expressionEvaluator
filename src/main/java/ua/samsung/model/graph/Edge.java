package ua.samsung.model.graph;

public class Edge<T> 
{
	private T _source;
	
	private T _target;
	
	public Edge(T source, T target)
	{
		_source = source;
		_target = target;
	}

	public T getSource() {
		return _source;
	}

	public T getTarget() {
		return _target;
	}
}
