package graph.DepthFirstSearch.classical;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author yifengguo
 * Make a deep copy of an undirected graph, there could be cycles in the original graph.

	Assumptions
	
	The given graph is not null
 */
/*
 * Demo:    node->     O  -----  O      original graph
 *                     | \     / | 
 *                     |  \   /  |
 *          mapping    |    O    |
 *                     |    |    |
 *                     O -- | -- O
 *                      \   |   /
 *                       \  |  /    
 *                          O           cloned graph
 *                         
 *                         
 *                         
 *      Time = O(V + E)  where V is vertices of graph and E is edges of graph
 *      Space = O(V)                   
 *                         
 *                         
 *                         
 *                         
 *                         
 */
class GraphNode {
	public int key;
	public List<GraphNode> neighbors;

	public GraphNode(int key) {
		this.key = key;
		this.neighbors = new ArrayList<GraphNode>();
	}
}

public class DeepCopyUndirectedGraph {
	public List<GraphNode> copy(List<GraphNode> graph) {
		List<GraphNode> res = new ArrayList<>();
		if (graph == null) {
			return res;
		}
		Map<GraphNode, GraphNode> map = new HashMap<>();
		for (GraphNode node : graph) {
			map.put(node, new GraphNode(node.key));
			clone(node, map); // deep copy by dfs
		}
		res.addAll(map.values());
		return res;
	}

	private void clone(GraphNode node, Map<GraphNode, GraphNode> map) {
		// base case
		if (node == null) {
			return;
		}
		GraphNode copyNode = map.get(node); // every time go into recursion, initialize a copyNode of current node
		                                    // for each node in the graph must be copied including their neighbors info
		for (GraphNode neighbor : node.neighbors) {
			if (!map.containsKey(neighbor)) { // avoid duplicate copy
				map.put(neighbor, new GraphNode(neighbor.key)); // map neighbor to copied graph
				clone(neighbor, map); // dfs
			}
			copyNode.neighbors.add(map.get(neighbor)); // construct neighbors info for current copied node
		}
	}
}
