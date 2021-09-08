public class SubstringMatch {
    private SubstringMatch(){}

    //字符串暴力匹配
    public static int bruteForce(String s,String t){
        if(s.length()<t.length()){
            return -1;
        }

        //长度计算：s.length()除去t.length()再加1匹配t.length中的第一个元素
        for(int i=0;i<s.length()-t.length()+1;i++){
            int j = 0;
            for(;j<t.length();j++){
                if(s.charAt(i+j)!=t.charAt(j)){
                    break;
                }
            }
            if (j == t.length()) {
                return i;
            }
        }
        return -1;
    }

    public static int rabinKarp(String s,String t){
        if(t.length()>s.length()){
            return -1;
        }else if(t.length()==0){
            return 0;
        }

        long MOD = (long) 1e9 + 7;
        long B = 256;
        long P = 1;
        for(int i=0;i<t.length()-1;i++){
            P = P * B % MOD;
        }
        //计算字符串t的哈希值
        long tHash = 0;
        for(int i=0;i<t.length();i++){
            tHash = (tHash * B + t.charAt(i)) % MOD;
        }
        //将前t.length()-1个位置字符串的哈希值计算出来
        long hash = 0;
        for(int i=0;i<t.length()-1;i++){
            hash = (hash * B + s.charAt(i)) % MOD;
        }
        //滚动哈希
        for(int i=t.length()-1;i<s.length();i++){
            hash = (hash * B + s.charAt(i))%MOD;
            if(hash==tHash && equal(s,i-t.length()+1,t)){
                return i - t.length() + 1;
            }
            hash = (hash - s.charAt(i - t.length() + 1) * P % MOD + MOD) % MOD;
        }
        return -1;
    }

    private static boolean equal(String s,int l,String t){
        for(int i=0;i<t.length();i++){
            if(t.charAt(i)!=s.charAt(l+i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s=FileOperation.readFile("A-Tale-of-Two-Cities.txt");
        String t = "city";
        SubstringMatchingHelper.matchTest("BruteForce",s,t);
        SubstringMatchingHelper.matchTest("rk",s,t);

        int n = 100000, m = 10000;

        StringBuilder s1 = new StringBuilder();
        StringBuilder t1 = new StringBuilder();
        for(int i=0;i<n;i++){
            s1.append("a");
        }
        for(int i=0;i<m;i++){
            t1.append("a");
        }
        t1.append("b");
        SubstringMatchingHelper.matchTest("BruteForce",s1.toString(),t1.toString());
        SubstringMatchingHelper.matchTest("rk",s1.toString(),t1.toString());
    }
}
