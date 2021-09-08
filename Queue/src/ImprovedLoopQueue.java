public class ImprovedLoopQueue<E> implements  Queue<E> {
    private E[] data;
    private int front, tail;

    public ImprovedLoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
    }

    public ImprovedLoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    private void resize(int newCapacity) {
        //实现resize
        int previousSize = getSize();
        E[] resizedData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < previousSize; i++) {
            resizedData[i] = data[(i + front) % data.length];
        }
        front = 0;
        tail = previousSize;
        data = resizedData;
    }

    @Override
    public int getSize() {
        //不使用size
        if (tail < front) {
            return tail + data.length - front;
        } else {
            return tail - front;
        }
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[(tail) % data.length] = e;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty!");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        if (getSize() < getCapacity() / 4 && getCapacity() / 2 > 1) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size=%d, Capacity=%d\n", getSize(), getCapacity()));
        res.append("front [");
        //另一种遍历的方法
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }


    public static void main(String[] args) {
        //测试ImprovedLoopQueue
        ImprovedLoopQueue<Integer> queue = new ImprovedLoopQueue<>(5);
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
