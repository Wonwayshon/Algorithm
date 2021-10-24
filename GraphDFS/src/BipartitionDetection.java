import java.util.ArrayList;

public class BipartitionDetection {
    private Graph graph;
    private boolean[] visited;
    private int[] colors;
    private boolean isBipartite = true;

    public BipartitionDetection(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
        colors = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if(!visited[v]){
                if(!dfs(v, 0)){
                    isBipartite = false;
                }
            }
        }
    }

    private boolean dfs(int v,int color){
        visited[v] = true;
        colors[v] = color;

        for(int w:graph.adj(v)){
            if(!visited[w]){
                if(!dfs(w, 1 - color)){
                    return false;
                }
            }else{
                if(colors[w]==colors[v]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Graph g = new GraphImplement("GraphDFS/g.txt");
        BipartitionDetection bipartitionDetection = new BipartitionDetection(g);
        System.out.println(bipartitionDetection.isBipartite);
        Graph g2 = new GraphImplement("GraphDFS/g2.txt");
        BipartitionDetection bipartitionDetection2 = new BipartitionDetection(g2);
        System.out.println(bipartitionDetection2.isBipartite);
        Graph g3 = new GraphImplement("GraphDFS/g3.txt");
        BipartitionDetection bipartitionDetection3 = new BipartitionDetection(g3);
        System.out.println(bipartitionDetection3.isBipartite);
    }
}
