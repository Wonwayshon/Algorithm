import java.util.Arrays;

public class MergeSortDemo {
    private MergeSortDemo(){}

    public static <E extends Comparable> void sort(E[] arr){
        sort(arr,0,arr.length-1);
    }

    //递归算法
    //将数组arr下表l~r的部分完成排序
    public static <E extends Comparable> void sort(E[] arr,int l,int r){
        if(l>=r){
            return;
        }
        int mid=l+(r-l)/2;
        sort(arr,l,mid);
        System.out.println("arr[l= "+l+"~mid= "+mid+"] sorted ! "+showArr(arr,l,mid));
        sort(arr, mid + 1, r);
        System.out.println("arr[mid+1= "+(mid+1)+"~r= "+r+"] sorted ! "+showArr(arr,mid+1,r));
        merge(arr, l, mid, r);
        System.out.println("arr[l= "+l+" mid+1= "+(mid+1)+" r= "+r+"] merged ! "+showArr(arr,l,r));
    }

    //将两个有序数组合并
    public static <E extends Comparable> void merge(E[] arr,int l,int mid,int r){
        E[] temp=Arrays.copyOfRange(arr, l, r + 1);

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

    //将传入数组下表l~r的部分合并成字符串返回
    private static <E extends Comparable> String showArr(E[] arr,int l,int r){
        StringBuilder res = new StringBuilder();
        res.append("arr["+l+"~"+r+"]:");
        for(int i=l;i<=r;i++){
            res.append(i+" ");
        }
        return res.toString();
    }

    public static void main(String[] args) {
        int n=10;
        Integer[] arr=ArrayGenerator.generateRandomArray(n, n);
        SortingHelper.sortTest("MergeSortDemo",arr);
    }
}
