import java.util.ArrayList;
import java.util.Collections;

public class SingleSoursePathOptimized {
    private Graph graph;
    private int[] pre;
    private int s;

    public SingleSoursePathOptimized(Graph graph, int s){
        this.graph = graph;
        this.s = s;
        pre = new int[graph.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s, s);
    }

    private void dfs(int v,int parent){
        pre[v] = parent;

        for(int w:graph.adj(v)){
            if(pre[w]==-1){
                dfs(w, v);
            }
        }

    }

    public Iterable<Integer> path(int v){
        ArrayList<Integer> res = new ArrayList<>();
        int cur = v;
        if(!isConnected(v)){
            return res;
        }
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public boolean isConnected(int v){
        ((GraphImplement)graph).validateVertex(v);
        return pre[v]!=-1;
    }

    public static void main(String[] args) {
        Graph g = new GraphImplement("GraphDFS/g.txt");
        SingleSoursePathOptimized ssPath = new SingleSoursePathOptimized(g,0);
        System.out.println("0 -> 6: "+ssPath.path(6));
    }
}