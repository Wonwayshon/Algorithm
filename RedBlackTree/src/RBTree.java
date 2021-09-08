import java.util.ArrayList;

public class RBTree<K extends Comparable<K>,V>{
    private static final boolean BLACK=false;
    private static final boolean RED=true;

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;
        public boolean color;

        public Node(K key,V value){
            this.key=key;
            this.value=value;
            this.left=null;
            this.right=null;
            this.color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root=null;
        size = 0;
    }

    //颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = node.right.color = BLACK;
    }

    //左旋转
    private Node leftRotate(Node node){
        Node y = node.right;
        node.right = y.left;
        y.left = node;
        y.color = node.color;
        node.color = RED;
        return y;
    }

    //右旋转
    private Node rightRotate(Node node){
        Node y = node.left;
        node.left = y.right;
        y.right = node;
        y.color = node.color;
        node.color = RED;
        return y;
    }

    public void add(K key, V value) {
        root=add(root, key, value);
        //根结点为黑色结点
        root.color=BLACK;
    }

    //递归方法 向以node为根节点的红黑树添加元素(key,value)
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

        if(isRed(node.right) && !isRed(node.left)){
            node=leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node=rightRotate(node);
        }
        if(isRed(node.right) && isRed(node.left)){
            flipColors(node);
        }

        return node;
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

        if(key.compareTo(node.key)<0){
            node.left = remove(node.left, key);
            return node;
        }else if(key.compareTo(node.key)>0){
            node.right=remove(node.right,key);
            return node;
        }else{
            if(node.left==null){
                size--;
                return node.right;
            }else if(node.right==null){
                size--;
                return node.left;
            }else{
                Node successor=minimum(node.left);
                successor.right=removeMin(node.left);
                node.left=node.right=null;
                return node;
            }
        }
    }

    //判断结点node的颜色
    private boolean isRed(Node node){
        if(node==null){
            return BLACK;
        }
        return node.color;
    }

    //返回以node为根的二分搜索树的最小值所在结点
    private Node minimum(Node node){
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }

    //删除掉以node为根的二分搜索树中的最小节点 返回删除后新的二分搜索树的根
    private Node removeMin(Node node){
        if(node.left==null){
            size--;
            return node.right;
        }

        node.left = removeMin(node.left);
        return node;
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
        RBTree<String,Integer> map=new RBTree<>();
        for(String word:list){
            if(map.contains(word)){
                map.set(word,map.get(word)+1);
            }else{
                map.add(word,1);
            }
        }
        System.out.println("Total different words:"+map.getSize());
        System.out.println("Frequency of two:"+map.get("two"));
    }
}
