public class AllPairsPath {
    private Graph g;
    private SingleSourcePath[] paths;

    public AllPairsPath(Graph g){
        this.g = g;
        for (int i = 0; i < g.V(); i++) {
            paths[i] = new SingleSourcePath(g, i);
        }
    }

    public boolean isConnection(int v,int w){
        ((GraphImplement)g).validateVertex(v);
        //((GraphImplement)g).validateVertex(w);
        return paths[v].isConnected(w);
    }

    public Iterable<Integer> path(int v,int w){
        ((GraphImplement)g).validateVertex(v);
        //((GraphImplement)g).validateVertex(w);
        return paths[v].path(w);
    }

}
