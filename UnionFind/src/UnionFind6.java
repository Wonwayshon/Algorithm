public class UnionFind6 implements UF{
    private int[] parent;
    private int[] sz;

    public UnionFind6(int size){
        parent = new int[size];
        sz = new int[size];
        for(int i=0;i<parent.length;i++){
            parent[i]=i;
            sz[i]=1;
        }
    }

    private int find(int p){
        if(p<0 || p>=parent.length){
            throw new IllegalArgumentException("Illegal index!");
        }
        if(p!=parent[p]){
            parent[p]=find(parent[p]);
        }
        return parent[p];
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p)==find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if(pRoot==qRoot) {
            return;
        }else if(sz[pRoot]<sz[qRoot]){
            parent[pRoot]=qRoot;
        }else if(sz[pRoot]<sz[qRoot]){
            parent[qRoot]=pRoot;
        }else if(sz[pRoot]==sz[qRoot]){
            parent[pRoot]=qRoot;
            sz[qRoot]++;
        }
    }
}
