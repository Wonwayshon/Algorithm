public class SegmentTree <E>{
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr,Merger<E> merger){
        this.merger = merger;
        data=(E[])new Object[arr.length];
        for(int i=0;i<arr.length;i++){
            data[i]=arr[i];
        }

        tree=(E[])new Object[4*data.length];
        buildSegmentTree(0,0,arr.length-1);
    }

    private void buildSegmentTree(int treeRoot,int l,int r){
        if(l==r){
            tree[treeRoot] = data[l];
            return;
        }
        int leftChild = leftChild(treeRoot);
        int rightChild = rightChild(treeRoot);

        int mid=l+(r-l)/2;
        buildSegmentTree(leftChild,l,mid);
        buildSegmentTree(rightChild,mid+1,r);
        tree[treeRoot]= merger.merge(tree[leftChild],tree[rightChild]);

    }

    //返回区间[queryL,queryR]的值
    public E query(int queryL,int queryR){
        if(queryL<0 || queryL>=data.length || queryR<0 || queryR>=data.length || queryL>queryR){
            throw new IllegalArgumentException("Illegal index!");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //返回在以treeRoot为根的表示[l,r]范围的子树中[queryL,queryR]的值
    private E query(int treeRoot,int l,int r,int queryL,int queryR){
        if(queryL==l && queryR==r){
            return tree[treeRoot];
        }

        int mid = l + (r - l) / 2;
        int lChild = leftChild(treeRoot);
        int rChild = rightChild(treeRoot);
        if(queryL>=mid+1){
            return query(rChild, mid + 1, r, queryL, queryR);
        }else if(queryR<=mid){
            return query(lChild, l, mid, queryL, queryR);
        }

        return merger.merge(query(lChild,l,mid,queryL,mid),query(rChild,mid+1,r,mid+1,queryR));
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index) {
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Illegal index!");
        }
        return data[index];
    }

    public void set(int index,E e){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("Illegal index!");
        }
        data[index]=e;
        set(0,0,data.length-1,index,e);
    }

    //递归方法，在以treeRoot为根表示范围为[l,r]的线段树中更新元素
    public void set(int treeRoot,int l,int r,int index,E e){
        if(l==r){
            tree[treeRoot] = e;
            return;
        }
        int mid=l+(r-l)/2;
        int leftChild=leftChild(treeRoot);
        int rightChild = rightChild(treeRoot);
        if(index<=mid){
            set(leftChild, l, mid, index, e);
        }else{
            set(rightChild, mid + 1, r, index, e);
        }
        tree[treeRoot] = merger.merge(tree[leftChild], tree[rightChild]);
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("[");
        for(int i=0;i<tree.length;i++){
            if(tree[i]!=null) {
                res.append(tree[i]);
            }else{
                res.append("null");
            }
            if(i!=tree.length-1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }
}
