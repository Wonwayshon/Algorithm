import org.junit.Test;

import java.util.Random;

public class ArrayGenerator {
    private ArrayGenerator(){}

    public static Integer[] generateOrderedArray(int n){
        Integer[] arr=new Integer[n];
        for(int i=0;i<n;i++){
            arr[i]=i;
        }
        return arr;
    }

    //生成长度为n的随机数组,每个数字的范围为[0,bound)
    public static Integer[] generateRandomArray(int n,int bound){
        Integer[] arr=new Integer[n];
        Random random=new Random();
        for(int i=0;i<n;i++){
            arr[i]=random.nextInt(bound);
        }
        return arr;
    }

    //生成当标定点取中间值使快速排序退化的特殊数组
    public static Integer[] generateSpecialArray(int n){
        Integer[] arr=new Integer[10];
        generateSpecialArray(arr,0,arr.length-1,0);
        return arr;
    }

    private static void generateSpecialArray(Integer[] arr,int l,int r,int value){
        if(l>r){
            return;
        }
        int mid=l+(r-l)/2;
        arr[l]=value;
        generateSpecialArray(arr,l+1,r,value+1);
        swap(arr,l,mid);
    }

    private static  void swap(Integer[] arr,int j,int i){
        int temp= arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


}
