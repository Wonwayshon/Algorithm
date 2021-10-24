import java.util.ArrayList;
import java.util.Collections;

public class Path {
    private Graph graph;
    private boolean[] visited;
    private int[] pre;
    private int s;
    private int t;

    public Path(Graph graph, int s, int t) {
        this.graph = graph;
        ((GraphImplement)graph).validateVertex(t);
        ((GraphImplement)graph).validateVertex(s);
        this.s = s;
        this.t = t;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        for (int i = 0; i < pre.length; i++) {
            pre[i] = -1;
        }
        dfs(s, s);

//        for(int i=0;i< visited.length;i++){
//            System.out.print(visited[i]+" ");
//        }
    }

    private boolean dfs(int v,int parent){
        visited[v] = true;
        pre[v] = parent;

        if (v == t) {
            return true;
        }

        for(int w:graph.adj(v)){
            if(!visited[w]){
                if(dfs(w, v)) return true;
            }
        }

        return false;
    }

    public Iterable<Integer> path(){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnected()){
            return res;
        }

        int cur = t;
        while (cur != s) {
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }

    public boolean isConnected(){
        return visited[t];
    }

    public static void main(String[] args) {
        Graph g = new GraphImplement("GraphDFS/g.txt");
        Path path = new Path(g,0,6);
        System.out.println("0 -> 6: "+path.path());
    }
}