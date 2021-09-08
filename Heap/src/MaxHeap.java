public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(E[] arr){
        data = new Array<>(arr);
        if(arr.length!=1) {
            for (int i = parent(arr.length - 1); i >= 0; i--) {
                siftDown(i);
            }
        }
    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    //返回一个索引对应元素的父亲结点的索引
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Parent doesn't exist!");
        }
        return (index - 1) / 2;
    }

    //返回一个索引对应元素的左孩子结点的索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    //返回一个索引对应元素的右孩子结点的索引
    private int rightChild(int index){
        return index * 2 + 2;
    }

    //向堆中添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    private void siftUp(int index){
        while(index>0 && data.get(parent(index)).compareTo(data.get(index))<0){
            data.swap(parent(index),index);
            index = parent(index);
        }
    }

    //取出堆中的最大元素
    public E extractMax(){
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int index){
        while(leftChild(index)<data.getSize()){
            int target=leftChild(index);
            if(rightChild(index)<data.getSize() &&
                    data.get(rightChild(index)).compareTo(data.get(leftChild(index)))>0){
                target=rightChild(index);
            }
            if(data.get(target).compareTo(data.get(index))<=0){
                break;
            }
            data.swap(index,target);
            index=target;
        }
    }

    //查看堆中最大元素
    public E findMax(){
        if(data.getSize()==0){
            throw new IllegalArgumentException("Empty Heap!");
        }
        return data.get(0);
    }

    //取出堆中的最大元素并添加一个元素取代
    public E replace(E e){
        E ret=findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
