import java.util.Arrays;

public class QuickSortOptimized {
    private QuickSortOptimized(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        sort(arr,0, arr.length-1);
    }

    public static <E extends Comparable<E>> void sort(E[] arr,int l,int r){
//        if(l>=r){
//            return;
//        }

        if(r-l<16){
            InsertionSort.sort(arr,l,r);
            return;
        }
        int p=partition(arr,l,r);
        sort(arr,l,p-1);
        sort(arr,p+1,r);
    }

    private static <E extends Comparable<E>> int partition(E[] arr,int l,int r){
        // arr[l+1,j] <v  arr[j+1,i] >=v
        int j=l;

        for(int i=l+1;i<=r;i++){
            if(arr[i].compareTo(arr[l])<0){
                j++;
                swap(arr,j,i);
            }
        }
        swap(arr,l,j);
        return j;
    }

    private static <E extends Comparable<E>> void swap(E[] arr,int j,int i){
        E temp= arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int n=1000000;
        Integer[] arr=ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr1= Arrays.copyOf(arr,arr.length);
        Integer[] arr2= Arrays.copyOf(arr,arr.length);
        SortingHelper.sortTest("QuickSort",arr);
        SortingHelper.sortTest("MergeSort",arr1);
        SortingHelper.sortTest("MergeSortOptimized",arr2);
    }
}
