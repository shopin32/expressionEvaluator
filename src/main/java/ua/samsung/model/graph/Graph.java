package ua.samsung.model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<TVertex,TEdge extends Edge<TVertex>> 
{
	private Map<TVertex, Set<TVertex>> _structure;
	
	public Graph()
	{
		_structure = new HashMap<>();
	}
	
	public Set<TVertex> getVertices()
	{
		return _structure.keySet(); 
	}
	
	public void addEdge(TVertex source, TVertex target)
	{
		addVertex(source);
		addVertex(target);
		
		_structure.get(source).add(target);
	}
	
	public void addVertex(TVertex vertex)
	{
		if(!_structure.containsKey(vertex))
		{
			_structure.put(vertex, new HashSet<>());
		}
	}
	
	public List<Edge<TVertex>> getEdges()
	{
		List<Edge<TVertex>> result = new ArrayList<>();
		for(TVertex source: getVertices())
		{
			for(TVertex target: _structure.get(source))
			{
				result.add(new Edge<>(source, target));
			}
		}
		return result;
	}
	
	public Set<TVertex> successors(TVertex vertex)
	{
		Set<TVertex> result = new HashSet<>();
		if(!_structure.containsKey(vertex)) return result;
		result.addAll(_structure.get(vertex));
		return result;
	}
	
	public Set<TVertex> predecessors(TVertex vertex)
	{
		Set<TVertex> result = new HashSet<>();
		
		for(TVertex source: getVertices())
		{
			if(successors(source).contains(vertex)) 
			{
				result.add(source);
			}
		}
		return result;
	}
	
}
