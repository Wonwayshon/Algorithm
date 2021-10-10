import java.util.ArrayList;

public class GraphDFS {
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private Graph graph;
    private boolean[] visited;

    public GraphDFS(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if(!visited[v]){
                dfs(v);
            }
        }
    }

    private void dfs(int v){
        visited[v] = true;
        pre.add(v);

        for(int w:graph.adj(v)){
            if(!visited[w]){
                dfs(w);
            }
        }

        post.add(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("GraphDFS/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }
}
