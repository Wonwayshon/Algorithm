public class HeapSort {
    private HeapSort(){};

    public static <E extends Comparable<E>> void sort2(E[] arr){
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for(E e:arr){
            maxHeap.add(e);
        }
        for(int i=arr.length-1;i>=0;i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        if(arr.length<=1){
            return;
        }
        for(int i=(arr.length-2)/2;i>=0;i--){
            siftDown(arr,arr.length,i);
        }
        for (int size=arr.length-1;size>0;size--){
            swap(arr,0,size);
            siftDown(arr,size,0);
        }

//        int size=arr.length;
//        while(size>1){
//            swap(arr,0,size-1);
//            size--;
//            siftDown(arr,size-1,0);
//        }
    }

    private static <E extends Comparable<E>> void siftDown(E[] data,int size,int index){
        while(leftChild(index)<size){
            int target=leftChild(index);
            if(rightChild(index)<size &&
                    data[rightChild(index)].compareTo(data[leftChild(index)])>0){
                target=rightChild(index);
            }
            if(data[target].compareTo(data[index])<=0){
                break;
            }
            swap(data,index,target);
            index=target;
        }
    }

    //返回一个索引对应元素的左孩子结点的索引
    private static int leftChild(int index){
        return index * 2 + 1;
    }

    //返回一个索引对应元素的右孩子结点的索引
    private static int rightChild(int index){
        return index * 2 + 2;
    }

    private static <E extends Comparable<E>> void swap(E[] arr,int i,int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
