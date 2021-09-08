//leetcode1147使用哈希思想
class Solution2 {
    private long MOD = (long) (1e9 + 7);
    private long[] pow26;

    public int longestDecomposition(String text) {
        pow26=new long[text.length()];
        pow26[0] = 1;
        for(int i=1;i<text.length();i++){
            pow26[i] = pow26[i - 1] * 26 % MOD;
        }
        return solve(text, 0, text.length()-1);
    }

    //对字符串[left,right]范围进行分段
    private int solve(String s,int left,int right){
        if(left>right){
            return 0;
        }

        long preHash = 0;
        long postHash = 0;
        for(int i=left,j=right;i<j;i++,j--){
            //根据上一次计算结果计算哈希值
            preHash = (preHash * 26 + (s.charAt(i) - 'a')) % MOD;
            postHash = (postHash + (s.charAt(j) - 'a') * pow26[right - j]) % MOD;

            //判断字符串中[left,i]与[j,right]段是否相等
            if(preHash==postHash && equal(s,left,i,j,right)){
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