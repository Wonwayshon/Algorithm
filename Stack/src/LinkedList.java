public class LinkedList<E> {
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
    private Node dummyHead;
    private int size;

    public LinkedList(){
        dummyHead=new Node(null,null);
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
            Node prev = dummyHead;
            for (int i = 0; i < index; i++) {
                prev = prev.next;
            }
            prev.next=new Node(e,prev.next);
            size++;
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
        Node cur=dummyHead.next;
        for(int i=0;i<index;i++){
            cur=cur.next;
        }
        return cur.e;
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
        Node cur=dummyHead.next;
        for(int i=0;i<index;i++){
            cur=cur.next;
        }
        cur.e=e;
    }

    //查找链表中是否存在元素e
    public boolean contains(E e){
//        Node cur=dummyHead.next;
//        while(cur.next!=null){
//            if(cur.e.equals(e)){
//                return true;
//            }
//            cur=cur.next;
//        }
//        return false;

        for(Node cur=dummyHead.next;cur.next!=null;cur=cur.next){
            if(cur.e.equals(e)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        for(Node cur=dummyHead.next;cur!=null;cur=cur.next){
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
        Node prev=dummyHead;
        for(int i=0;i<index;i++){
            prev=prev.next;
        }
        Node retNode=prev.next;
        prev.next=retNode.next;
        retNode.next=null;
        size--;
        return retNode.e;
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
