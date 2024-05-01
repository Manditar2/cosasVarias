import java.util.LinkedList;
import java.util.Queue;

/*
EJERCICIO:

You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 */

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        boolean error = true;
        for (int i = 0; i < times.length; i++){
            if (times[i][0] == k){
                error = false;
                break;
            }
        }
        if (error){
            return -1;
        }
        Weightedgraph graph = new Weightedgraph(n+1);
        Edge edge;
        for (int i = 0; i < times.length; i ++){
            edge = new Edge(times[i][0], times[i][1], times[i][2]);
            graph.addEdge(edge);
        }
        BellmanFord solution = new BellmanFord(graph, k);
        int[] d = solution.get_distance();
        int max = d[1];
        for (int i = 1; i < n + 1; i++){
            if (d[i] == Integer.MAX_VALUE){
                return -1;
            }
            if (d[i] > max && d[i] != Integer.MAX_VALUE)
                max = d[i];
        }
        return max;
    }
}

class BellmanFord{
    private int[] distancia;
    private Queue<Integer> cola = new LinkedList<>();

    public BellmanFord(Weightedgraph graph, int s){
        int current_node;
        int size = graph.size();
        this.distancia = new int[size];
        for (int i = 0; i < size; i++)
            this.distancia[i] = Integer.MAX_VALUE;
        this.distancia[s] = 0;
        cola.add(s);
        
        while(!cola.isEmpty()){
            current_node = cola.remove();
            relax(graph.get_edges(current_node)); 
        }
    }
    public void relax(LinkedList<Edge> edges){
        for (Edge edge: edges){
            if (distancia[edge.get_u()] + edge.get_w() < distancia[edge.get_v()]){
                distancia[edge.get_v()] = distancia[edge.get_u()] + edge.get_w();
                cola.add(edge.get_v());
            }
        }
    }
    public int[] get_distance(){
        return this.distancia;
    }
}

class Weightedgraph {
    public final int size;
    private LinkedList<Edge>[] adj;

    public Weightedgraph(int size){
        this.size = size;
        adj = (LinkedList<Edge>[]) new LinkedList[size];
        for (int i = 0; i < size; i++)
            adj[i] = new LinkedList<Edge>();
    }
    public void addEdge(Edge e){
        adj[e.get_u()].add(e);
    }
    public int size(){
        return size;
    }
    public LinkedList<Edge> get_edges(int vertex){
        return adj[vertex];
    }
}

class Edge
{
    private final int u;
    private final int v;
    private final int w;

    public Edge(int u, int v, int w)
    {
        this.u = u;
        this.v = v;
        this.w = w;
    }
    public int get_w(){
        return w;
    }
    public int get_u(){
        return u;
    }
    public int get_v(){
        return v;
    }
}

/*
 Su bellman ford de manual (pero sin verificación de ciclos jeje)
 */
