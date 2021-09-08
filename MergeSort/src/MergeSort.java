import java.util.Arrays;

public class MergeSort {
    private MergeSort(){}

    public static <E extends Comparable> void sort(E[] arr){
        sort(arr,0,arr.length-1);
    }

    //递归算法
    //将数组arr下表l~r的部分完成排序
    public static <E extends Comparable> void sort(E[] arr,int l,int r){
        if(l>=r){
            return;
        }

//        if(r-l<=15){
//            InsertionSort.sort(arr,l,r);
//            return;
//        }

        int mid=l+(r-l)/2;
        sort(arr,l,mid);
        sort(arr, mid + 1, r);
        if(arr[mid].compareTo(arr[mid+1])>0) {
            merge(arr, l, mid, r);
        }
    }

    //将两个有序数组合并
    public static <E extends Comparable> void merge(E[] arr,int l,int mid,int r){
        E[] temp=Arrays.copyOf(arr, arr.length);

        int i=l;
        int j=mid+1;

        for(int k=l;k<=r;k++){
            if(i>mid){
                arr[k]=temp[j-l];
                j++;
            }else if(j>r){
                arr[k]=temp[i-l];
                i++;
            }else if(temp[i-l].compareTo(temp[j-l])<=0){
                arr[k]=temp[i-l];
                i++;
            }else{
                arr[k]=temp[j-l];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n=100000;
        Integer[] arr=ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("MergeSort",arr);
    }
}
