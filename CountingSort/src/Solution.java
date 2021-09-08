//LeetCode75
class Solution {
    public void sortColors(int[] nums) {
        int R=3;
        //处理元素取值范围为[0,R)的计数排序
        int[] cnt = new int[R];
        for(int num:nums){
            cnt[num]++;
        }
        //[index[i],index[i+1])的值为i
        int[] index = new int[R+1];
        for(int i=0;i<R;i++){
            index[i + 1] = index[i] + cnt[i];
        }
        for(int i=0;i<R;i++){
            for(int j=index[i];j<index[i+1];j++){
                nums[j] = i;
            }
        }
    }
}