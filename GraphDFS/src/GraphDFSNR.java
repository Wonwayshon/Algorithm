import java.util.ArrayList;
import java.util.Stack;

public class GraphDFSNR {
    private ArrayList<Integer> order = new ArrayList<>();
    private boolean[] visited;
    private Graph graph;

    public GraphDFSNR(Graph graph) {
        this.graph = graph;
        visited = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int init){
        Stack<Integer> stack = new Stack<>();
        int v;
        stack.push(init);
        visited[init] = true;
        while (!stack.isEmpty()) {
            v=stack.pop();
            order.add(v);
            for (int w : graph.adj(v)) {
                if(!visited[w]){
                    visited[w] = true;
                    stack.push(w);
                }
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Graph g = new GraphImplement("GraphDFS/g.txt");
        GraphDFSNR graphDFSNR = new GraphDFSNR(g);
        System.out.println(graphDFSNR.order());
    }
}
