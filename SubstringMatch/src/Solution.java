//leetcode1147
class Solution {
    public int longestDecomposition(String text) {
        return solve(text, 0, text.length()-1);
    }

    //对字符串[left,right]范围进行分段
    private int solve(String s,int left,int right){
        if(left>right){
            return 0;
        }

        for(int i=left,j=right;i<j;i++,j--){
            //判断字符串中[left,i]与[j,right]段是否相等
            if(equal(s,left,i,j,right)){
                return 2+solve(s,i+1,j-1);
            }
        }
        return 1;
    }

    //判断字符串中[l1,l2]与[r1,r2]段是否相等
    private boolean equal(String s,int l1,int l2,int r1,int r2){
        if(l1>l2 || r1>r2){
            throw new IllegalArgumentException("Illegal range!");
        }

        for(int i=l1,j=r1;i<=l2 && j<=r2;i++,j++){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
        }
        return true;
    }
}