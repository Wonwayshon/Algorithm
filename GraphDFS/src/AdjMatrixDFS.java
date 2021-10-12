import java.util.ArrayList;

public class AdjMatrixDFS {
    private ArrayList<Integer> pre = new ArrayList<>();
    private ArrayList<Integer> post = new ArrayList<>();
    private boolean[] visited;
    private AdjMatrix adjMatrix;

    public AdjMatrixDFS(AdjMatrix adjMatrix) {
        this.adjMatrix = adjMatrix;
        visited = new boolean[adjMatrix.V()];
        for (int v = 0; v < adjMatrix.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    private void dfs(int v){
        pre.add(v);
        visited[v] = true;
        for (int w : adjMatrix.adj(v)) {
            if (!visited[w]) {
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
        AdjMatrix adjMatrix = new AdjMatrix("GraphDFS/g.txt");
        AdjMatrixDFS adjMatrixDFS = new AdjMatrixDFS(adjMatrix);
        System.out.println(adjMatrixDFS.pre());
        System.out.println(adjMatrixDFS.post());
    }

}
