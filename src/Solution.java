import java.util.*;

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        Map<DirectedGraphNode, Integer> indegree = getIndegree(graph);
        ArrayList<DirectedGraphNode> startNodes = getStartNodes(graph,indegree);
        ArrayList<DirectedGraphNode> topSortList = getTopSort(indegree,startNodes);

        if(topSortList.size() == graph.size()){
            return topSortList;
        }
        return null;
    }

    public Map<DirectedGraphNode, Integer> getIndegree(ArrayList<DirectedGraphNode> graph){

        Map<DirectedGraphNode, Integer> indegree = new HashMap<>();

        for(DirectedGraphNode node : graph){
            for(DirectedGraphNode neighbor : node.neighbors){
                if(!indegree.containsKey(neighbor)){
                    indegree.put(neighbor,1);
                }else{
                    indegree.put(neighbor,indegree.get(neighbor)+1);
                }
            }
        }
        return indegree;
    }

    public ArrayList<DirectedGraphNode> getStartNodes(ArrayList<DirectedGraphNode> graph , Map<DirectedGraphNode, Integer> indegree){

        ArrayList<DirectedGraphNode> startNodes = new ArrayList<>();

        for(DirectedGraphNode node : graph){
            if(!indegree.containsKey(node)){
                startNodes.add(node);
            }
        }
        return startNodes;
    }

    public ArrayList<DirectedGraphNode> getTopSort(Map<DirectedGraphNode, Integer> indegree, ArrayList<DirectedGraphNode> startNodes){

        Queue<DirectedGraphNode> queue = new LinkedList<>(startNodes);
        ArrayList<DirectedGraphNode> topSortList = new ArrayList<>(startNodes);

        while(!queue.isEmpty()){
            DirectedGraphNode node = queue.poll();
            for(DirectedGraphNode neighbor : node.neighbors){
                indegree.put(neighbor, indegree.get(neighbor) -1);
                if(indegree.get(neighbor) == 0 ){
                    queue.offer(neighbor);
                    topSortList.add(neighbor);
                }
            }
        }
        return topSortList;
    }

}