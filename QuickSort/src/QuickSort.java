import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private QuickSort(){}

    public static <E extends Comparable<E>> void sort(E[] arr){
        sort(arr,0, arr.length-1,new Random());
    }

    public static <E extends Comparable<E>> void sort(E[] arr,int l,int r,Random rnd){
        if(l>=r){
            return;
        }
        int p=partition(arr,l,r,rnd);
        sort(arr,l,p-1,rnd);
        sort(arr,p+1,r,rnd);
    }

    private static <E extends Comparable<E>> int partition(E[] arr,int l,int r,Random rnd){
        int k = rnd.nextInt(r - l + 1)+l;
        swap(arr, l, k);

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

    //双路快排
    public static <E extends Comparable<E>> void sort2Ways(E[] arr){
        sort2Ways(arr,0, arr.length-1,new Random());
    }

    public static <E extends Comparable<E>> void sort2Ways(E[] arr,int l,int r,Random rnd){
        if(l>=r){
            return;
        }
        int p=partition2(arr,l,r,rnd);
        sort2Ways(arr,l,p-1,rnd);
        sort2Ways(arr,p+1,r,rnd);
    }

    private static <E extends Comparable<E>> int partition2(E[] arr,int l,int r,Random rnd){
        int k = rnd.nextInt(r - l + 1)+l;
        swap(arr, l, k);

        int i=l+1,j=r;
        while(true){
            while(i<=j && arr[l].compareTo(arr[i])>=0){
                i++;
            }
            while(i<=j && arr[l].compareTo(arr[j])<=0){
                j--;
            }
            if(i>=j){
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }

    //三路快排
    public static <E extends Comparable<E>> void sort3Ways(E[] arr){
        sort3Ways(arr,0, arr.length-1,new Random());
    }

    public static <E extends Comparable<E>> void sort3Ways(E[] arr,int l,int r,Random rnd){
        if(l>=r){
            return;
        }
        int p=rnd.nextInt(r-l+1)+l;
        swap(arr, l, p);

        //arr[l+1,lt]<v arr[lt+1,i-1]==v arr[gt,r]>v
        int i=l+1,lt=l,gt=r+1;
        while(i<gt){
            if(arr[l].compareTo(arr[i])>0){
                lt++;
                swap(arr, lt, i);
                i++;
            }else if(arr[l].compareTo(arr[i])<0){
                gt--;
                swap(arr, gt, i);
            }else{
                i++;
            }
        }
        swap(arr, l, lt);
        //arr[l,lt-1]<v arr[lt,gt-1]=v arr[gt,r]>v
        sort3Ways(arr,l,lt-1,rnd);
        sort3Ways(arr,gt,r,rnd);
    }

    private static <E extends Comparable<E>> void swap(E[] arr,int j,int i){
        E temp= arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int n=10000;
        System.out.println("Ordered Array");
        Integer[] arr=ArrayGenerator.generateOrderedArray(n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("QuickSort3Ways",arr);
        SortingHelper.sortTest("QuickSort2Ways",arr2);

        System.out.println("SameElem Array");
        arr=ArrayGenerator.generateRandomArray(n,1);
        arr2 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("QuickSort3Ways",arr);
        SortingHelper.sortTest("QuickSort2Ways",arr2);
    }
}
