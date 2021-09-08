import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//leetcode 187 滚动哈希
class Solution6 {
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length()<=10){
            return new ArrayList<String>();
        }

        HashSet<Long> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        int[] map = new int[256];
        map['A'] = 1;
        map['T'] = 2;
        map['C'] = 3;
        map['G'] = 4;
        long hash = 0;
        long ten9 = (long) 1e9;

        //将前九个位置字符串的哈希值计算出来
        for(int i=0;i<9;i++){
            hash = hash * 10 + map[s.charAt(i)];
        }

        for(int i=9;i<s.length();i++){
            hash = hash * 10 + map[s.charAt(i)];
            if(seen.contains(hash)){
                res.add(s.substring(i - 9, i + 1));
            }else{
                seen.add(hash);
            }
            hash = hash - map[s.charAt(i - 9)]*ten9;
        }

        return new ArrayList<String>(res);
    }

}