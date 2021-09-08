public class ImprovedLoopQueueWW<E> implements Queue<E>{
    private E[] data;
    private int front,tail,size;

    public ImprovedLoopQueueWW(int capacity){
        data=(E[])new Object[capacity];
        front=0;
        tail=0;
        size=0;
    }

    public ImprovedLoopQueueWW(){
        this(10);
    }

    public int getCapacity(){
        return data.length;
    }

    private void resize(int newCapacity){
        //实现resize
        E[] resizedData=(E[])new Object[newCapacity];
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
        return size==0;
    }

    @Override
    public void enqueue(E e) {
        if(size==getCapacity()) {
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
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        //测试ImprovedLoopQueueWithoutWaste
        ImprovedLoopQueueWW<Integer> queue = new ImprovedLoopQueueWW<>(5);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}

