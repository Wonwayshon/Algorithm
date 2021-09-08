//leetcode 1392 超时
public class Solution3 {
    public String longestPrefix(String s) {
        //比较长度为len的前缀和后缀[0,len-1],[,s.length()-1]
        for(int len=s.length()-1;len>=1;len--){
            if(equal(s,0,len-1,s.length()-len,s.length()-1)){
                return s.substring(0, len);
            }
        }
        return "";
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
