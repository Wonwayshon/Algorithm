import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphBFS {
    private Graph graph;
    private boolean[] visited;
    private List<Integer> order = new ArrayList<>();

    public GraphBFS(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if(!visited[v]){
                bfs(v);
            }
        }
    }

    private void bfs(int v){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        while(!queue.isEmpty()) {
            int cur=queue.remove();
            order.add(cur);
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args){
        Graph g = new GraphImplement("GraphBFS/g.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS Order : " + graphBFS.order());
    }
}
