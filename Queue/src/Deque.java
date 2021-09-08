public class Deque<E> {
    private E[] data;
    private int startIdx,endIdx,size;

    //构造方法初始化参数
    public Deque(int capacity){
        data=(E[])new Object[capacity];
        startIdx=0;
        endIdx=0;
        size=0;
    }

    //默认容量为10
    public Deque(){
        this(10);
    }

    //获取队列容量
    public int getCapacity(){
        return data.length;
    }

    //获取队列存量
    public int getSize(){
        return size;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return size==0;
    }

    //判断队列是否已满
    public boolean isFull(){
        return getSize()==getCapacity();
    }

    //队列增缩容
    private void resize(int capacity){
       E[] resizedData=(E[])new Object[capacity];
       for(int i=0;i<getSize();i++){
           resizedData[i]=data[(i+startIdx)%getCapacity()];
       }
       data=resizedData;
       startIdx=0;
       endIdx=getSize()-1;
    }

    //向队列的前端增加
    public void addFront(E e){
        if(isEmpty()){
            data[startIdx]=e;
            size++;
            return;
        }
        if(isFull()){
            resize(getCapacity()*2);
        }
        if(--startIdx<0){
            startIdx+=getCapacity();
        }
        data[startIdx]=e;
        size++;
    }

    //向队列的后端增加
    public void addLast(E e){
        if(isEmpty()){
            data[endIdx]=e;
            size++;
            return;
        }
        if(isFull()){
            resize(getCapacity()*2);
        }
        endIdx=(++endIdx)%getCapacity();
        data[endIdx]=e;
        size++;
    }

    //从队列的前端删除
    public void removeFront(){
        if(isEmpty()){
            throw new IllegalArgumentException("Deque is empty!");
        }
        data[startIdx]=null;
        size--;
        if(isEmpty()){
            startIdx=endIdx=0;
            size=0;
        }else {
            startIdx = ++startIdx % getCapacity();
            if (getSize() < getCapacity() / 4 && getCapacity() > 2) {
                resize(getCapacity() / 2);
            }
        }
    }

    //从队列的后端删除
    public void removeLast(){
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty!");
        }
        data[endIdx]=null;
        size--;
        if(isEmpty()){
            startIdx=endIdx=0;
            size=0;
        }else {
            if (--endIdx < 0) {
                endIdx += getCapacity();
            }
            if (getSize() < getCapacity() / 4 && getCapacity() > 2) {
                resize(getCapacity() / 2);
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append(String.format("Deque: size=%d, Capacity=%d\n",getSize(),getCapacity()));
        res.append("front [");
        for(int i=0;i<size; i++){
            res.append(data[(i+startIdx)%getCapacity()]);
            if(i!=size-1){
                res.append(", ");
            }
        }
        res.append("] last");
        return res.toString();
    }

    //测试Deque
    public static void main(String[] args) {
        //在下面双端队列测试中向队首队尾分别加入0-19并同时删除队首队尾能够整除3的数
        Deque deque=new Deque();
        for(int i=0;i<20;i++){
            deque.addFront(i);
            deque.addLast(i);
            System.out.println(deque);
            if(i%3==0){
                deque.removeFront();
                deque.removeLast();
                System.out.println(deque);
            }
        }
        System.out.println(deque);

        //在下面的双端队列的测试中，偶数从队尾加入；奇数从队首加入
//        Deque<Integer> dq = new Deque<>();
//        for(int i = 0 ; i < 16 ; i ++){
//            if(i % 2 == 0) dq.addLast(i);
//            else dq.addFront(i);
//            System.out.println(dq);
//        }
//
//        // 之后，我们依次从队首和队尾轮流删除元素
//        System.out.println();
//        for(int i = 0; !dq.isEmpty(); i ++){
//            if(i % 2 == 0) dq.removeFront();
//            else dq.removeLast();
//            System.out.println(dq);
//        }
    }
}
