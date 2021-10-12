import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdjMatrix implements Graph {
    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            V = scanner.nextInt();
            if(V<0){
                throw new IllegalArgumentException("Error Negative V");
            }
            E = scanner.nextInt();
            if(E<0){
                throw new IllegalArgumentException("Error Negative V");
            }
            adj = new int[V][V];
            for(int i=0;i<E;i++){
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);

                if(a==b){
                    throw new IllegalArgumentException("Self Loop Not Supported!");
                }
                if(adj[a][b]==1){
                    throw new IllegalArgumentException("Parallel Edges Not Supported!");
                }

                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v){
        if(v<0 || v>=V){
            throw new IllegalArgumentException("Illegal Argument v = "+v);
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public boolean hasEdge(int v,int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v][w] == 1;
    }

    public ArrayList<Integer> adj(int v){
        validateVertex(v);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<V;i++){
            if(adj[v][i]==1){
                list.add(i);
            }
        }
        return list;
    }

    public int degree(int v){
        return adj(v).size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V=%d, E=%d\n", V, E));
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("GraphBasics/g.txt");
        System.out.println(adjMatrix);
    }
}
