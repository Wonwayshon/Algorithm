import java.util.Arrays;

public class LSDSort {
    private LSDSort(){}

    public static void sort(String[] arr,int W){
        //检查数组中字符串长度是否一致
        for(String s:arr){
            if(s.length()!=W){
                throw new IllegalArgumentException("String length error!");
            }
        }

        int R = 256;
        int[] cnt = new int[R];
        int[] index = new int[R + 1];
        String[] temp = new String[arr.length];
        for(int r=W-1;r>=0;r--){
            Arrays.fill(cnt,0);
            for(String s:arr){
                cnt[s.charAt(r)]++;
            }

            index[0] = 0;
            for(int i=0;i<R;i++){
                index[i + 1] = index[i] + cnt[i];
            }

            for(String s:arr){
                temp[index[s.charAt(r)]] = s;
                index[s.charAt(r)]++;
            }

            for(int i=0;i<arr.length;i++){
                arr[i] = temp[i];
            }
        }
    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CAB", "ACB", "BAC", "ABC", "CBA"};
        LSDSort.sort(arr, 3);
        for(String s: arr)
            System.out.println(s);
    }
}
