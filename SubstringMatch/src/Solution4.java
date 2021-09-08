import org.junit.Test;

import java.util.Arrays;

//leetcode 1392 哈希思想
public class Solution4 {
    private long MOD = (long) (1e9 + 7);

    //@Test
    public String longestPrefix(String s) {
//        String s = "level";
        //pow26[i]=26^i%MOD
        long[] pow26 = new long[s.length()];
        pow26[0] = 1;
        for (int i = 1; i < s.length(); i++) {
            pow26[i] = pow26[i - 1] * 26 % MOD;
        }

        //计算哈希值
        //preHash[i]=hash([0,i])
        long[] preHash = new long[s.length()];
        //postHash[i]=hash([s.length()-1-i,s.length()-1])
        long[] postHash = new long[s.length()];
        preHash[0] = s.charAt(0)-'a';
        postHash[0] = s.charAt(s.length() - 1) - 'a';
        //i+1=len
        for(int i=1;i<s.length();i++){
            preHash[i] = (preHash[i - 1] * 26 + (s.charAt(i)-'a')) % MOD;
            postHash[i] = (postHash[i - 1] + (s.charAt(s.length() - 1 - i) - 'a') * pow26[i]) % MOD;
        }
//        System.out.println("preHash:"+ Arrays.toString(preHash));
//        System.out.println("postHash:"+ Arrays.toString(postHash));

        //比较长度为len的前缀和后缀[0,len-1],[s.length()-len,s.length()-1]
        for(int len=s.length()-1;len>=1;len--){
//            System.out.println("len:"+len);
//            System.out.println(preHash[len-1]);
//            System.out.println(postHash[len-1]);
//            System.out.println("isEuqal:"+equal(s,0,len-1,s.length()-len,s.length()-1));
            if(preHash[len-1]==postHash[len-1] && equal(s,0,len-1,s.length()-len,s.length()-1)){
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
