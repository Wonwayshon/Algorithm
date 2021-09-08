import java.util.Collections;
import java.util.LinkedList;

public class BucketSort {
    private BucketSort(){}

    public static void sort(Integer[] arr,int B){
        if(B<=1){
            throw new IllegalArgumentException("Illegal B!");
        }

        Integer[] temp = new Integer[arr.length];
        sort(arr, 0, arr.length - 1, B, temp);
    }

    //递归函数
    private static void sort(Integer[] arr,int left,int right,int B,Integer[] temp){
        if(left>=right){
            return;
        }

        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;
        for(int i=left;i<right;i++){
            maxv = Math.max(arr[i], maxv);
            minv = Math.min(arr[i], minv);
        }

        if(minv==maxv){
            return;
        }

        int d=(maxv-minv+1)/B + ((maxv-minv)%B>0?1:0);
        Integer[] cnt = new Integer[B];
        Integer[] index = new Integer[B + 1];

        for(int i=left;i<=right;i++){
            cnt[(arr[i]-minv)/d]++;
        }

        for(int i=0;i<B;i++){
            index[i + 1] = index[i] + cnt[i];
        }

        for(int i=left;i<=right;i++){
            int p = (arr[i]-minv) / d;
            temp[left+index[p]]=arr[i];
            index[p]++;
        }

        for(int i=left;i<right;i++){
            arr[i] = temp[i];
        }

        sort(arr,left,left+index[0]-1,B,temp);
        for(int i=0;i<B-1;i++){
            sort(arr,left+index[i],left+index[i+1]-1,B,temp);
        }
    }

    //c表示一个桶中装的元素个数
    public static void sort2(Integer[] arr,int c){
        if(c<=0){
            throw new IllegalArgumentException("Illegal c!");
        }

        //计算桶的个数B
        int B = arr.length / c + ((arr.length % c>0) ? 1 : 0);
        LinkedList<Integer>[] buckets = new LinkedList[B];
        for(int i=0;i<B;i++){
            buckets[i] = new LinkedList<>();
        }

        //求出数组内的最大最小值
        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){
            maxv = Math.max(maxv, arr[i]);
            minv = Math.min(minv, arr[i]);
        }

        //求出每个桶内装的元素的范围range
        int range = (maxv - minv + 1) / B + (((maxv - minv + 1) % B > 0) ? 1 : 0);

        //将arr中的元素放到对应的桶中
        for(int e:arr){
            buckets[(e - minv) / range].add(e);
        }

        //对每个桶进行排序
        for(int i=0;i<B;i++){
            Collections.sort(buckets[i]);
        }

        //将桶中元素按顺序放回原数组
        int index = 0;
        for(int i=0;i<B;i++){
            for(int num:buckets[i]){
                arr[index++] = num;
            }
        }
    }
}
