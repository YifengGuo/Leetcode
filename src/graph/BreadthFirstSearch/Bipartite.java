package graph.BreadthFirstSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author yifengguo
 *
Graph
Bipartite
Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes 
can be divided into two groups such that no nodes have direct edges to other nodes in the same group.
	
	Examples
	
	1  --   2
	
	    /   
	
	3  --   4
	
	is bipartite (1, 3 in group 1 and 2, 4 in group 2).
	
	1  --   2
	
	    /   |
	
	3  --   4
	
	is not bipartite.
	
	Assumptions
	
	The graph is represented by a list of nodes, and the list of nodes is not null.
 */
/*
 * basic idea: BFS to traverse all the connected neighbors of current node
 *             use a hash_map to maintain the relationship of node and the
 *             group it belongs.
 *             check each neighbor of current expanded node and see if the
 *             neighbor belongs to wrong group since all neighbors' group 
 *             shall be different from expanded node's group if the graph
 *             is bipartite
 * time = O(V)
 * space = O(V)
 */
class GraphNode {
	int key;
	List<GraphNode> neighbors;
	public GraphNode(int key) {
		this.key = key;
		this.neighbors = new ArrayList<>();
	}
}
public class Bipartite {
	public boolean isBipartite(List<GraphNode> graph) {
		if (graph == null || graph.isEmpty()) {
			return true;
		}
		// use a hash_map to record if a graphnode which belongs
		// to a certain group is visited or not
		// key: current graph node, value: the group it belongs to
		Map<GraphNode, Integer> visited = new HashMap<>();
		for (GraphNode node : graph) {
			// run bfs on each node of graph and check the validate
			// relationship between expanded node and its neighbors
			if (!bfs(node, visited)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean bfs(GraphNode node, Map<GraphNode, Integer> visited) {
		// if we have visited the node, we do not have to run bfs
		// on this node again, this prunes the duplicate operations
		if (visited.containsKey(node)) {
			return true;
		}
		// put the current node into random group, say, group 0
		// then all the neighbors of it shall belong to group 1
		// if the graph is bipartite
		visited.put(node, 0);
		// run bfs
		Queue<GraphNode> queue = new LinkedList<>();
		queue.offer(node);
		while (!queue.isEmpty()) {
			GraphNode cur = queue.poll();
			for (GraphNode nei : cur.neighbors) {
				int groupId = visited.get(cur);
				// the neighbors shall all belong to opposite
				// group of current node if the graph is bipartite
				int neiId = groupId == 0 ? 1 : 0;
				if (!visited.containsKey(nei)) { // if current neighbor of cur has not been
					                             // visited, put it into map and queue
					visited.put(nei, neiId);
					queue.offer(nei);
				} else if (visited.get(nei) != neiId) { // if current neighbor has been visited
					                                    // but it belongs to current node's group
					                                    // then the graph is not bipartite
					return false;
				}
				// else the neighbor node has been visited and it belongs to right group
				// then we do nothing
			}
		}
		return true;
	}
}
