package ua.samsung.model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<TVertex,TEdge extends Edge<TVertex>> 
{
	private Map<TVertex, List<TEdge>> _structure;
	
	public Graph()
	{
		_structure = new HashMap<>();
	}
	
	public void addEdge(TVertex vertex, TEdge edge)
	{
		if(!_structure.containsKey(vertex)) return;
		
		_structure.get(vertex).add(edge);
	}
	
	public Set<TVertex> getVertices()
	{
		return _structure.keySet(); 
	}
	
	public void addVertex(TVertex vertex)
	{
		if(!_structure.containsKey(vertex))
		{
			_structure.put(vertex, new ArrayList<>());
		}
	}
	
	public List<TEdge> getEdges()
	{
		List<TEdge> result = new ArrayList<>();
		for(TVertex source: getVertices())
		{
			result.addAll(_structure.get(source));
		}
		return result;
	}
	
	public Set<TVertex> successors(TVertex vertex)
	{
		Set<TVertex> result = new HashSet<>();
		if(!_structure.containsKey(vertex)) return result;
		for(TEdge edge: _structure.get(vertex))
		{
			result.add(edge.getTarget());
		}
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
