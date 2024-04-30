import java.util.Queue;
import java.util.LinkedList;

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
            else {
                continue;
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