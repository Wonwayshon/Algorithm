import java.util.*;

public class SingleSourcePath {
    private Graph graph;
    private int s;
    private boolean[] visited;
    private int[] pre;

    public SingleSourcePath(Graph graph,int s){
        graph.validateVertex(s);
        this.graph = graph;
        visited = new boolean[graph.V()];
        pre = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            pre[i] = -1;
        }
        bfs(s);
    }

    private void bfs(int v){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        pre[v]=v;
        while(!queue.isEmpty()) {
            int cur=queue.remove();
            for (int w : graph.adj(cur)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                    pre[w]=cur;
                }
            }
        }
    }

    public boolean isConnected(int t){
        graph.validateVertex(t);
        return visited[t];
    }

    public Iterable<Integer> path(int t){
        ArrayList<Integer> res = new ArrayList<>();
        if(!isConnected(t)){
            return res;
        }
        int cur=t;
        while(cur!=s){
            res.add(cur);
            cur = pre[cur];
        }
        res.add(s);
        Collections.reverse(res);
        return res;
    }


    public static void main(String[] args){
        Graph g = new GraphImplement("GraphBFS/g.txt");
        SingleSourcePath ssp = new SingleSourcePath(g,0);
        System.out.println("0->6 :  " + ssp.path(6));
    }
}
