import java.util.ArrayList;

public class BST<K extends Comparable,V>{

    private class Node{
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key,V value){
            this.key=key;
            this.value=value;
            this.left=null;
            this.right=null;
        }
    }

    private Node root;
    private int size;

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

}
