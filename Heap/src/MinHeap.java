import java.util.Random;

public class MinHeap<E extends Comparable<E>> {
    private Array<E> data;

    public MinHeap(){
        data = new Array<>();
    }

    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MinHeap(E[] arr){
        data = new Array<>(arr);
        for(int i=parent(arr.length-1);i>=0;i--){
            siftDown(i);
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    private int parent(int index){
        if(index==0){
            throw new IllegalArgumentException("Parent doesn't exists!");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int index){
        while(index>0 && data.get(parent(index)).compareTo(data.get(index))>0){
            data.swap(parent(index),index);
            index = parent(index);
        }
    }

    public E findMin(){
        if(data.getSize()==0){
            throw new IllegalArgumentException("Min doesn't exists!");
        }
        return data.get(0);
    }

    public E extractMin(){
        E ret=findMin();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int index){
        while(leftChild(index)<data.getSize()){
            int k = leftChild(index);
            if(rightChild(index)<data.getSize() &&
                    data.get(leftChild(index)).compareTo(data.get(rightChild(index)))>0){
                k = rightChild(index);
            }
            if(data.get(k).compareTo(data.get(index))>0){
                break;
            }
            data.swap(k,index);
            index=k;
        }
    }

    public E replace(E e){
        E ret = findMin();
        data.set(0,e);
        siftDown(0);
        return ret;
    }

    public static void main(String[] args) {

        int n = 1000000;

        MinHeap<Integer> minHeap = new MinHeap<>();
        Random random = new Random();
        for(int i = 0 ; i < n ; i ++)
            minHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for(int i = 0 ; i < n ; i ++)
            arr[i] = minHeap.extractMin();

        for(int i = 1 ; i < n ; i ++)
            if(arr[i-1] > arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MinHeap completed.");
    }
}
