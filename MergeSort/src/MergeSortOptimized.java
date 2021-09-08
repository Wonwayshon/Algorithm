import java.util.Arrays;

public class MergeSortOptimized {
    private MergeSortOptimized(){}

    public static <E extends Comparable> void sort(E[] arr){
        //E[] temp=Arrays.copyOfRange(arr,0,arr.length);
        E[] temp=(E[])new Comparable[arr.length];
        sort(arr,0,arr.length-1,temp);
    }

    //递归算法
    //将数组arr下表l~r的部分完成排序
    public static <E extends Comparable> void sort(E[] arr,int l,int r,E[] temp){
        if(l>=r){
            return;
        }

//        if(r-l<=15){
//            InsertionSort.sort(arr,l,r);
//            return;
//        }

        int mid=l+(r-l)/2;
        sort(arr,l,mid,temp);
        sort(arr, mid + 1, r,temp);
        if(arr[mid].compareTo(arr[mid+1])>0) {
            merge(arr, l, mid, r,temp);
        }
    }

    //自底向上
    public static <E extends Comparable> void sortBU(E[] arr){
        E[] temp=Arrays.copyOfRange(arr,0,arr.length);

        int n= arr.length;

        for(int sz=1;sz<n;sz+=sz){

            //合并数组的[i,i+sz-1]和[i+sz,i+sz+sz-1]部分
            for(int i=0;i+sz<n;i+=sz+sz){
                if(arr[i+sz-1].compareTo(arr[i+sz])>0) {
                    merge(arr, i, i + sz-1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    public static <E extends Comparable> void sortBUOptimized(E[] arr){
        E[] temp=Arrays.copyOfRange(arr,0,arr.length);

        int n= arr.length;
        for(int i=0;i<n;i+=16){
            InsertionSort.sort(arr,i,Math.min(i+15,n-1));
        }

        for(int sz=16;sz<n;sz+=sz){
            //合并数组的[i,i+sz-1]和[i+sz,i+sz+sz-1]部分
            for(int i=0;i+sz<n;i+=sz+sz){
                if(arr[i+sz-1].compareTo(arr[i+sz])>0) {
                    merge(arr, i, i + sz-1, Math.min(i + sz + sz - 1, n - 1), temp);
                }
            }
        }
    }

    //将两个有序数组合并
    public static <E extends Comparable> void merge(E[] arr,int l,int mid,int r,E[] temp){
        System.arraycopy(arr,l,temp,l,r-l+1);

        int i=l;
        int j=mid+1;

        for(int k=l;k<=r;k++){
            if(i>mid){
                arr[k]=temp[j];
                j++;
            }else if(j>r){
                arr[k]=temp[i];
                i++;
            }else if(temp[i].compareTo(temp[j])<=0){
                arr[k]=temp[i];
                i++;
            }else{
                arr[k]=temp[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int n=100000;
        Integer[] arr=ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("MergeSortOptimizedBUOptimized",arr);
    }
}
