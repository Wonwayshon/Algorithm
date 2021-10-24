import java.util.ArrayList;

public class CycleDetection {

    private Graph graph;
    private boolean[] visited;

    public CycleDetection(Graph graph){
        this.graph = graph;
        visited = new boolean[graph.V()];
    }

    private boolean dfs(int v,int parent){
        visited[v] = true;
        for(int w:graph.adj(v)){
            if(visited[w]){
                if(parent!=w){
                    return true;
                }
            }else{
                if(dfs(w,v)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasCycle(){
        for (int v = 0; v < graph.V(); v++) {
            if(!visited[v]){
                if(dfs(v,v)){
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Graph g = new GraphImplement("GraphDFS/g.txt");
        CycleDetection cycleDetection = new CycleDetection(g);
        System.out.println(cycleDetection.hasCycle());
        Graph g2 = new GraphImplement("GraphDFS/g2.txt");
        CycleDetection cycleDetection2 = new CycleDetection(g2);
        System.out.println(cycleDetection2.hasCycle());
    }
}
