import java.util.Queue;
import java.util.LinkedList;

/* 
There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.
*/ 

public class IsBipartite {
    private boolean[] appended;
    private int[] whichSet;
    private Queue<Integer> onQ = new LinkedList<Integer>();

    public boolean isBipartite(int[][] graph) {
        boolean ans = true;
        appended = new boolean[graph.length];
        whichSet = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!appended[i]){
                if (graph.length == 0) {
                    ans = true && ans;
                    appended[i] = true;
                }
                else{
                    ans = recorrer(graph, i) && ans;
                }
            }
        }
        return ans;
    }

    public boolean recorrer(int[][] graph, int vertex){
        int currentNodo = vertex;
        for (int i = 0; i < appended.length; i++) appended[i] = false;
        onQ.add(currentNodo);
        appended[currentNodo] = true;
        whichSet[currentNodo] = 0;

        while (!onQ.isEmpty()) {
            currentNodo = onQ.poll();
            for (int i = 0; i < graph[currentNodo].length; i++){
                int temp = graph[currentNodo][i];
                if (!appended[temp]){
                    appended[temp] = true;
                    whichSet[temp] = 1 - whichSet[currentNodo];
                    onQ.add(temp);
                }
                else{
                    if (whichSet[temp] == whichSet[currentNodo]) return false;
                    else if (whichSet[temp] == 1 - whichSet[currentNodo]) continue;
                    else return false;
                }
            }
        }
        return true;
    }
}