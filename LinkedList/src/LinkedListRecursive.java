import javafx.util.Pair;

public class LinkedListRecursive<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e=e;
            this.next=next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    //private Node head;
    private Node head;
    private int size;

    public LinkedListRecursive(){
        head=null;
        size=0;
    }

    //获得长度
    public int getSize(){
        return size;
    }

    //向链表头部插入元素
//    public void addFirst(E e){
//        head=new Node(e,head);
//        size++;
//    }
    public void addFirst(E e){
        add(e,0);
    }

    //判断是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //向索引为index处插入元素
    public void add(E e,int index){
        if(index<0||index>size){
            throw new IllegalArgumentException("illegal index");
        }
        //由于可能添加在第一个位置所以需要覆盖head
        head=add(head,index,e);
        size++;
    }

    //递归算法
    //方法完成向传入链表指定位置插入元素
    private Node add(Node node,int index,E e){
        if(index==0){
            return new Node(e,node);
        }
        //将链表拆成 头节点+已经完成插入的链表
        node.next=add(node.next,index-1,e);
        return node;
    }

    //往链表末尾添加新元素
    public void addLast(E e){
        add(e,size);
    }

    //获得链表在index位置的元素
    public E get(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("illegal index");
        }
        return get(head, index);
    }

    //递归算法
    //方法完成查找返回传入链表中指定位置的元素
    private E get(Node node,int index){
        if(index==0){
            return node.e;
        }
        //将链表拆成 头结点+返回指定位置元素的链表
        return get(node.next,index-1);
    }

    //获得链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    //获得链表的最后一个元素
    public E getLast(){
        return get(size-1);
    }

    //修改链表在index位置的元素
    public void set(E e,int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("illegal index");
        }
        //修改位置不好影响头结点的的指向所以不一定要求覆盖head
        head = set(head, e, index);
    }

    //递归方法
    //完成对传入链表修改指定位置元素的方法
    private Node set(Node node,E e,int index) {
        if(index==0){
            node.e=e;
            return node;
        }
        //将链表拆成 头节点+已经完成修改的链表
        node.next=set(node.next,e,index-1);
        return node;
    }
    //递归方法尝试
    private Node setTest(Node node,E e,int index) {
        if(node==null){
            return null;
        }
        if(index==0){
            node.e=e;
        }
        node.next=set(node.next,e,index-1);
        return node;
    }

    //查找链表中是否存在元素e
    public boolean contains(E e){
        return contains(head,e);
    }

    //递归算法
    //查找传入链表中是否存在元素e
    private boolean contains(Node node,E e){
        if(node.e.equals(e)){
            return true;
        }
        if(node==null){
            return false;
        }
        return contains(node.next,e);
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        for(Node cur=head;cur!=null;cur=cur.next){
            res.append(cur.e);
            res.append("->");
        }
        res.append("null") ;
        return res.toString();
    }

    //删除链表中index处的元素
    public E remove(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("illegal index");
        }
        Pair<Node,E> res=remove(head,index);
        size--;
        //可能删除的是头节点所以需要覆盖原head
        head=res.getKey();
        return res.getValue();
    }

    //递归方法
    //删除传入链表中指定位置处的元素
    private Pair<Node,E> remove(Node node, int index){
        if(index==0){
            return new Pair<Node,E>(node.next,node.e);
        }
        Pair<Node,E> res=remove(node.next,index-1);
        //将链表拆成 头节点+已经完成删除的链表
        node.next=res.getKey();
        return new Pair<>(node,res.getValue());
    }

    //从链表头删除元素
    public E removeFirst(){
        return remove(0);
    }

    //从链表末尾删除元素
    public E removeLast(){
        return remove(size-1);
    }
}
