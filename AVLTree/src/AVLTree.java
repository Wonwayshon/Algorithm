import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable,V> {

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        public Node(K key,V value){
            this.key=key;
            this.value=value;
            this.left=null;
            this.right=null;
            this.height=1;
        }
    }

    private Node root;
    private int size;

    //检查是否是BST
    public boolean isBST(){
        List<K> keys=new ArrayList<>();
        inOrder(root, keys);
        for(int i=1;i<keys.size();i++){
            if(keys.get(i-1).compareTo(keys.get(i))>0){
                return false;
            }
        }
        return true;
    }

    //中序遍历递归方法
    private void inOrder(Node node,List keys){
        if(node==null){
            return;
        }
        inOrder(node.left,keys);
        keys.add(node.key);
        inOrder(node.right,keys);
    }

    //检查是否是平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if(node==null){
            return true;
        }
        if(Math.abs(getBalanceFactor(node))>1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    //右旋转
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y){
        Node x=y.left;
        Node T3=x.right;
        x.right=y;
        y.left=T3;
        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;
        return x;
    }

    //左旋转
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y){
        Node x=y.right;
        Node T3=x.left;
        x.left=y;
        y.right=T3;
        y.height=Math.max(getHeight(y.left),getHeight(y.right))+1;
        x.height=Math.max(getHeight(x.left),getHeight(x.right))+1;
        return x;
    }

    public void add(K key, V value) {
        root=add(root, key, value);
    }

    //递归方法 向以node为根节点的二分搜索树添加元素(key,value)
    private Node add(Node node,K key,V value){
        if(node==null){
            size++;
            return new Node(key,value);
        }

        if(node.key.compareTo(key)>0){
            node.left=add(node.left,key,value);
        }else if(node.key.compareTo(key)<0) {
            node.right = add(node.right, key, value);
        }else{
            node.value=value;
        }

        node.height= 1+Math.max(getHeight(node.left),getHeight(node.right));

        //平衡维护
        int balanceFactor=getBalanceFactor(node);
        int balanceFactorLeft=getBalanceFactor(node.left);
        int balanceFactorRight=getBalanceFactor(node.right);
        //LL
        if(balanceFactor>1 && balanceFactorLeft>=0){
            return rightRotate(node);
        }
        //RR
        if(balanceFactor<-1 && balanceFactorRight<=0){
            return leftRotate(node);
        }
        //LR
        if(balanceFactor>1 && balanceFactorLeft<0){
            node.left=leftRotate(node.left);
            return rightRotate(node);
        }
        //RL
        if(balanceFactor<-1 && balanceFactorRight>0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private int getHeight(Node node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node){
        if(node==null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public V remove(K key) {
        Node node=getNode(key);
        if(node!=null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    //递归方法 删除以node为根节点的二分搜索树中键为key的结点 返回删除后二分搜索树的根
    private Node remove(Node node,K key) {
        if(node==null){
            return null;
        }

        Node retNode;

        if(key.compareTo(node.key)<0){
            node.left = remove(node.left, key);
            retNode = node;
        }else if(key.compareTo(node.key)>0){
            node.right=remove(node.right,key);
            retNode = node;
        }else{
            if(node.left==null){
                size--;
                retNode = node.right;
            }else if(node.right==null){
                size--;
                retNode = node.left;
            }else{
                Node successor=minimum(node.left);
                successor.right=remove(node.left,successor.key);
                node.left=node.right=null;
                retNode = node;
            }
        }

        if(retNode==null){
            return null;
        }

        //平衡维护
        int balanceFactor=getBalanceFactor(retNode);
        int balanceFactorLeft=getBalanceFactor(retNode.left);
        int balanceFactorRight=getBalanceFactor(retNode.right);
        //LL
        if(balanceFactor>1 && balanceFactorLeft>=0){
            return rightRotate(retNode);
        }
        //RR
        if(balanceFactor<-1 && balanceFactorRight<=0){
            return leftRotate(retNode);
        }
        //LR
        if(balanceFactor>1 && balanceFactorLeft<0){
            node.left=leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        //RL
        if(balanceFactor<-1 && balanceFactorRight>0){
            node.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
    }

    //返回以node为根的二分搜索树的最小值所在结点
    private Node minimum(Node node){
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }


    public V get(K key) {
        Node node=getNode(key);
        return node==null?null:node.value;
    }

    private Node getNode(K key){
        Node node=root;
        while(node!=null){
            if(node.key.equals(key)){
                return node;
            }else if(node.key.compareTo(key)<0) {
                node=node.right;
            }else{
                node=node.left;
            }
        }
        return null;
    }

    public void set(K key, V newValue) {
        Node node=getNode(key);
        if(node==null){
            throw new IllegalArgumentException(key+"doesn't exists!");
        }
        node.value=newValue;
    }

    public boolean contains(K key) {
        return getNode(key)!=null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<>();
        FileOperation.readFile("A-Tale-of-Two-Cities.txt", list);
        System.out.println("Total:"+list.size());
        AVLTree<String,Integer> map=new AVLTree<>();
        for(String word:list){
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        System.out.println("Total different words:"+map.getSize());
        System.out.println("Frequency of two:"+map.get("two"));
        System.out.println("is BST:"+map.isBST());
        System.out.println("is Balanced:"+map.isBalanced());
        for(String word:list){
            map.remove(word);
            if(!map.isBalanced() || !map.isBST()){
                throw new RuntimeException("ERROR!");
            }
        }
    }
}
