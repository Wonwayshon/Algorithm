import java.util.*;

public class BST<E extends Comparable<E>> {
    private class Node{
        public E e;
        public Node left;
        public Node right;

        public Node(E e){
            this.e=e;
            this.left=null;
            this.right=null;
        }
    }

    private int size;
    private Node root;

    public BST(){
        size=0;
        root=null;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    //向二分搜索树添加新元素e
    public void add(E e){
        root=add(root,e);
    }

    //递归算法 向以node为根节点的二分搜索树添加元素e并返回插入新节点后二分搜索树的根
    private Node add(Node node,E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e)<0){
            node.left=add(node.left,e);
        }else if(e.compareTo(node.e)>0){
            node.right=add(node.right,e);
        }
        return node;
    }

    //非递归实现向二分搜索树添加新元素e
    public void addNonR(E e){
        if(root==null){
            root=new Node(e);
            size++;
            return;
        }
        Node prev=root;
        while(prev!=null){
            if(prev.e.compareTo(e)>0){
                if(prev.left==null){
                    prev.left=new Node(e);
                    size++;
                    return;
                }
                prev=prev.left;
            }else if(prev.e.compareTo(e)<0){
                if(prev.right==null){
                    prev.right=new Node(e);
                    size++;
                    return;
                }
                prev=prev.right;
            }else{
                return;
            }
        }
    }

    //查询二分搜索树中是否存在元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    //递归方法 查询以node为根节点的二分搜索树中是否存在元素e
    private boolean contains(Node node,E e){
        if(node==null){
            return false;
        }

        if(e.compareTo(node.e)==0){
            return true;
        }else if(e.compareTo(node.e)<0){
            return contains(node.left, e);
        }else{
            return contains(node.right, e);
        }
    }

    //前序遍历
    public void preOrder(){
        preOrder(root);
    }

    //递归方法 前序遍历以node为根节点的二分搜索树
    private void preOrder(Node node){
        if (node==null){
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    //非递归前序遍历
    public void preOrderNonR(){
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            Node node=stack.pop();
            System.out.println(node.e);
            if(node.right!=null) {
                stack.push(node.right);
            }
            if(node.left!=null) {
                stack.push(node.left);
            }
        }
    }

    //中序遍历
    public void inOrder(){
        inOrder(root);
    }

    //递归方法 中序遍历以node为根节点的二分搜索树
    private void inOrder(Node node){
        if(node==null){
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    //后序遍历
    public void postOrder(){
        postOrder(root);
    }

    //递归方法 后序遍历以node为根节点的二分搜索树
    private void postOrder(Node node){
        if(node==null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    //层序遍历
    public void levelOrder(){
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node cur=q.remove();
            System.out.println(cur.e);
            if(cur.left!=null) {
                q.add(cur.left);
            }
            if(cur.right!=null) {
                q.add(cur.right);
            }
        }
    }

    //寻找二分搜索树的最小元素
    public E minimum(){
        if(root==null){
            throw new IllegalArgumentException("BST is empty!");
        }
        return minimum(root).e;
    }

    //递归算法 返回以node为结点的二分搜索树的最小元素所在结点
    private Node minimum(Node node){
        if(node.left==null){
            return node;
        }
        return minimum(node.left);
    }

    //非递归
    public E minimumNonR(){
        Node cur=root;
        while(cur.left!=null){
            cur=cur.left;
        }
        return cur.e;
    }

    //寻找二分搜索树的最大元素
    public E maximum(){
        if(root==null){
            throw new IllegalArgumentException("BST is empty!");
        }
        return maximum(root).e;
    }

    //递归算法 返回以node为结点的二分搜索树的最小元素所在结点
    private Node maximum(Node node){
        if(node.right==null){
            return node;
        }
        return minimum(node.right);
    }

    //非递归
    public E maximumNonR(){
        Node cur=root;
        while(cur.right!=null){
            cur=cur.right;
        }
        return cur.e;
    }

    //删除二分搜索树最小元素所在结点并返回最小元素
    public E removeMin(){
        E ret=minimum();
        root=removeMin(root);
        return ret;
    }

    //递归算法 删除以node为根节点的二分搜索树最小元素所在结点并返回删除后新子树的根节点
    private Node removeMin(Node node){
        if(node.left==null){
            size--;
            return node.right;
        }

        node.left=removeMin(node.left);
        return node;
    }

    //删除二分搜索树最大元素所在结点并返回最小元素
    public E removeMax(){
        E ret=maximum();
        root=removeMax(root);
        return ret;
    }

    //递归算法 删除以node为根节点的二分搜索树最大元素所在结点并返回删除后新子树的根节点
    private Node removeMax(Node node){
        if(node.right==null){
            size--;
            return node.left;
        }

        node.right=removeMax(node.right);
        return node;
    }

    //从二分搜索树中删除元素e对应的结点
    public int remove(E e){
        //root = remove(root, e);
        int old_size = this.size;
        root = remove(root, e);
        return this.size;
    }

    //递归函数 删除以node为根节点中元素e对应的结点并返回完成删除后的根结点
    private Node remove(Node node,E e){
        if(node==null){
            return null;
        }
        if(node.e.compareTo(e)==0){
            if(node.left==null){
                size--;
                return node.right;
            }else if(node.right==null){
                size--;
                return node.left;
            }else {
                Node successor = minimum(node);
                successor.left = node.left;
                successor.right = removeMin(node);
                node.left = node.right = null;
                return successor;
            }
        }

        if(node.e.compareTo(e)>0){
            node.left=remove(node.left,e);
        }else if(node.e.compareTo(e)<0){
            node.right=remove(node.right,e);
        }
        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root,0,res);
        return res.toString();
    }

    //生成二叉树带深度字符串
    public void generateBSTString(Node node,int depth,StringBuilder res){
        if(node==null){
            res.append(generateDepthString(depth)+null+"\n");
            return;
        }
        res.append(generateDepthString(depth)+node.e+"\n");

        generateBSTString(node.left,depth+1,res);
        generateBSTString(node.right,depth+1,res);
    }

    //生成深度字符串
    public String generateDepthString(int depth){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }

//    public static void main(String[] args) {
//        BST<Integer> bst=new BST<>();
//        Random random=new Random();
//        int n=15;
//        ArrayList<Integer> arr = new ArrayList<>();
//        for(int i=0;i<n;i++){
//            bst.add(random.nextInt(100));
//        }
//        bst.inOrder();
//    }

    public static void main(String[] args) {
        BST<Integer> bst=new BST<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入结点数量：");
        int count = sc.nextInt();
        for(int i=0;i<count;i++){
            bst.add(sc.nextInt());
        }
        System.out.println("中序遍历：");
        bst.inOrder();
    }
}
