import java.util.Arrays;
import java.util.Random;

public class Main {
    private static double heapTester(Integer[] testArray,boolean isHeapify){
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if(isHeapify){
            maxHeap=new MaxHeap<>(testArray);
        }else{
            maxHeap = new MaxHeap<>(testArray.length);
            for(int num:testArray){
                maxHeap.add(num);
            }
        }
        long endTime = System.nanoTime();
        int[] arr = new int[testArray.length];
        for(int i=0;i<testArray.length;i++){
            arr[i]=maxHeap.extractMax();
        }
        for(int i=1;i<testArray.length;i++){
            if(arr[i-1]<arr[i]){
                throw new IllegalArgumentException("ERROR!");
            }
        }
        return (endTime-startTime)/1000000000.0;
    }

    private static void test1(){
        Random rnd = new Random();
        int n = 10000000;
        Integer[] testArray=new Integer[n];
        for(int i=0;i<n;i++){
            testArray[i]=(rnd.nextInt(Integer.MAX_VALUE));
        }
        double time1 = heapTester(testArray, false);
        double time2 = heapTester(testArray, true);
        System.out.println("Without Heapify: "+time1+"s");
        System.out.println("With Heapify: "+time2+"s");
    }

    public static void main(String[] args) {
        Random rnd = new Random();
        int n = 100;
        Integer[] testArray=new Integer[n];
        for(int i=0;i<n;i++){
            testArray[i]=(rnd.nextInt(100));
        }
        HeapSort.sort(testArray);
        for(int i=1;i<testArray.length;i++){
            System.out.print(testArray[i]+" ");
            if(testArray[i-1]>testArray[i]){
                throw new IllegalArgumentException("ERROR!");
            }
        }
    }
}
