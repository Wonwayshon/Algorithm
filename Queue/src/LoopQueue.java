public class LoopQueue<E> implements Queue<E>{
    private E[] data;
    private int front,tail,size;

    public LoopQueue(int capacity){
        data=(E[])new Object[capacity+1];
        front=0;
        tail=0;
        size=0;
    }

    public LoopQueue(){
        this(10);
    }

    public int getCapacity(){
        return data.length-1;
    }

    private void resize(int newCapacity){
        //利用dequeue实现resize dequeue中存在resize会出现问题
//        E[] resizedData=(E[])new Object[newCapacity+1];
//        for(int i=0;i<getCapacity();i++){
//            if(front!=tail) {
//                resizedData[i] =dequeue();
//            }else{
//                front=0;
//                tail=i;
//                data=resizedData;
//                break;
//            }
//        }

        //实现resize
        E[] resizedData=(E[])new Object[newCapacity+1];
        for(int i=0;i<size;i++){
            resizedData[i]=data[(i+front)%data.length];
        }
        front=0;
        tail=size;
        data=resizedData;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front==tail;
    }

    @Override
    public void enqueue(E e) {
        if((tail+1)%data.length==front) {
            resize(getCapacity() * 2);
        }
        data[(tail)%data.length] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Queue is empty!");
        }
        E ret=data[front];
        data[front]=null;
        front=(front+1)%data.length;
        size--;
        if(size<getCapacity()/4 && getCapacity()/2>1){
            resize(getCapacity()/2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append(String.format("Queue: size=%d, Capacity=%d\n",size,getCapacity()));
        res.append("front [");
        for(int i=0;i<size; i++){
            res.append(data[(i+front)%data.length]);
            if(i!=size-1){
                res.append(", ");
            }
        }
        //另一种遍历的方法
//        for(int i=front;i!=tail;i=(i+1)%data.length){
//            res.append(data[i]);
//            if((i+1)%data.length!=tail){
//                res.append(", ");
//            }
//        }
        res.append("] tail");
        return res.toString();
    }
}

