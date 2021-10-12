import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class CC {
    private Graph graph;
    private int[] visited;
    private int cccount;

    public CC(Graph graph){
        this.graph = graph;
        visited = new int[graph.V()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        for (int v = 0; v < graph.V(); v++) {
            if(visited[v]<0){
                dfs(v);
                cccount++;
            }
        }
    }

    private void dfs(int v){
        visited[v] = cccount;
        for(int w:graph.adj(v)){
            if(visited[w]<0){
                dfs(w);
            }
        }
    }

    public int count(){
        return cccount;
    }

    public boolean isConnected(int v,int w){
        ((GraphImplement)graph).validateVertex(v);
        ((GraphImplement)graph).validateVertex(w);
        return visited[v]==visited[w];
    }

    public ArrayList<Integer>[] components(){
        ArrayList<Integer>[] result=new ArrayList[cccount];
        for(int i=0;i<cccount;i++){
            result[i] = new ArrayList<Integer>();
        }
        for(int i=0;i< visited.length;i++){
            result[visited[i]].add(i);
        }
        return result;
    }

    public void viewVisited(){
        for (int i = 0; i < visited.length; i++) {
            System.out.print(visited[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Graph g = new GraphImplement("GraphDFS/g.txt");
        CC cc = new CC(g);
        System.out.println(cc.isConnected(0, 6));
        System.out.println(cc.isConnected(0, 5));
        ArrayList<Integer>[] arr = cc.components();
        for (int i = 0; i < cc.count(); i++) {
            System.out.print(i+" : ");
            for(int v:arr[i]){
                System.out.print(v+" ");
            }
            System.out.println();
        }
    }
}
