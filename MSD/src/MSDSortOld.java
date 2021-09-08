public class MSDSortOld {
    private MSDSortOld(){}

    public static void sort(String[] arr){
        sort(arr, 0, arr.length - 1, 0);
    }

    //递归方法
    public static void sort(String[] arr,int left,int right,int r){
        if(left>=right){
            return;
        }

        int R = 256;
        String[] temp=new String[right-left+1];
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
            temp[index[p]]=arr[i];
            index[p]++;
        }

        for(int i=left;i<=right;i++){
            arr[i] = temp[i - left];
        }

        //递归下去
        for(int i=0;i<R;i++){
            sort(arr,left+index[i],left+index[i+1]-1,r+1);
        }
    }

    public static void main(String[] args) {

        String[] arr = {"BCA", "CBAA", "AC", "BADFE", "ABC", "CBA"};
        MSDSortOld.sort(arr);
        for(String s: arr)
            System.out.println(s);
    }
}
