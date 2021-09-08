public class MSDSort {
    private MSDSort(){}

    public static void sort(String[] arr){
        int len = arr.length;
        String[] temp = new String[len];
        sort(arr, 0, len - 1, 0, temp);
    }

    //递归方法
    public static void sort(String[] arr,int left,int right,int r,String[] temp){
        if(left>=right){
            return;
        }

        int R = 256;
        int[] cnt = new int[R + 1];
        int[] index = new int[R + 2];

        for(int i=left;i<=right;i++){
            cnt[r >= arr[i].length() ? 0 : (arr[i].charAt(r) + 1)]++;
        }

        for(int i=0;i<R+1;i++){
            index[i + 1] = index[i] + cnt[i];
        }

        for(int i=left;i<=right;i++){
            int p = r >= arr[i].length() ? 0 : (arr[i].charAt(r)+1);
            temp[index[p]+left]=arr[i];
            index[p]++;
        }

        for(int i=left;i<=right;i++){
            arr[i] = temp[i];
        }

        //递归下去
        for(int i=0;i<R;i++){
            sort(arr,left+index[i],left+index[i+1]-1,r+1,temp);
        }
    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSort.sort(arr);
        for(String s: arr)
            System.out.println(s);
    }
}
