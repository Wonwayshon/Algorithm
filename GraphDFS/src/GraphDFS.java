import java.util.ArrayList;

public class GraphDFS {
    private ArrayList<Integer> order = new ArrayList<>();
    private Graph graph;
    private boolean[] visited;

    public GraphDFS(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        dfs(0);
    }

    private void dfs(int v){
        visited[v] = true;
        order.add(v);

        for(int w:graph.adj(v)){
            if(!visited[w]){
                dfs(w);
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public static void main(String[] args) {
        Graph g = new Graph("GraphDFS/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.order);
    }
}
