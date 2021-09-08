import java.util.Arrays;
import java.util.Random;

public class QuickSortWithDepth {
    private QuickSortWithDepth(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        sort(arr,0, arr.length-1,0);
    }

    public static <E extends Comparable<E>> void sort(E[] arr,int l,int r,int depth){
        if(l>=r){
            return;
        }
        System.out.print(generateDepth(depth));
        int p=partition(arr,l,r);
        System.out.println(String.format("partition arr[%d,%d]to[%d,%d][%d][%d,%d]",l,r,l,p-1,p,p+1,r));
        System.out.print(generateDepth(depth));
        System.out.println(String.format("sort arr[%d,%d]",l,p-1));
        sort(arr,l,p-1,++depth);
        System.out.print(generateDepth(depth));
        System.out.println(String.format("sort arr[%d,%d]",p+1,r));
        sort(arr,p+1,r,++depth);
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

    private static String generateDepth(int depth){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<depth;i++){
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int n=10;
        //Integer[] arr=ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr=ArrayGenerator.generateOrderedArray(n);
        SortingHelper.sortTest("QuickSortWithDepth",arr);
    }
}
