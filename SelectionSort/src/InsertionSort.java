import java.util.Arrays;

public class InsertionSort {

    private InsertionSort(){}

    public static <E extends Comparable<E>> void sort2(E[] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0 && arr[j+1].compareTo(arr[j])<0;j--){
                swap(arr,j+1,j);
            }

//            for(int j=i-1;j>=0;j--){
//                if(arr[j+1].compareTo(arr[j])<0){
//                    swap(arr,j+1,j);
//                }else{
//                    break;
//                }
////                for(E k:arr){
////                    System.out.print(k+" ");
////                }
////                System.out.println();
//            }
        }
    }

    public static <E extends Comparable<E>> void sort(E[] arr){
        for(int i=1;i<arr.length;i++){
            E temp=arr[i];
            int j;
            for(j=i;j-1>=0 && temp.compareTo(arr[j-1])<0;j--){
                arr[j]=arr[j-1];
            }
            arr[j]=temp;
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] arr){
        for(int i=arr.length-1;i>=0;i--){
            E temp=arr[i];
            int j;
            for(j=i;j+2<=arr.length && temp.compareTo(arr[j+1])>0;j++){
                arr[j]=arr[j+1];
                //测试
                for(E k:arr){
                    System.out.print(k+" ");
                }
                System.out.println();
            }
            arr[j]=temp;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] arr,int i,int j){
        E temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void main(String[] args) {
        Integer[] arr={6,5,5,2,1,4,3};
        InsertionSort.sort3(arr);
        for(int i:arr){
            System.out.print(i+" ");
        }
        System.out.println();

//        int[] dataSize={1000,10000,100000};
//        for(int n:dataSize) {
//            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
//            Integer[] arr2= Arrays.copyOf(arr,arr.length);
//            SortingHelper.sortTest("InsertionSort", arr);
//            SortingHelper.sortTest("InsertionSort2", arr2);
//        }

//        System.out.println("Random Array:");
//        int[] dataSize={1000,10000,100000};
//        for(int n:dataSize) {
//            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
//            Integer[] arr2= Arrays.copyOf(arr,arr.length);
//            SortingHelper.sortTest("InsertionSort", arr);
//            SortingHelper.sortTest("SelectionSort", arr2);
//            System.out.println();
//
//            System.out.println("Ordered Array:");
//            arr = ArrayGenerator.generateOrderedArray(n);
//            arr2= Arrays.copyOf(arr,arr.length);
//            SortingHelper.sortTest("InsertionSort", arr);
//            SortingHelper.sortTest("SelectionSort", arr2);
//            System.out.println();
//        }

    }
}
