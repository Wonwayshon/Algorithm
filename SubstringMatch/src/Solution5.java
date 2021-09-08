import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//leetcode 187
class Solution5 {
    public List<String> findRepeatedDnaSequences(String s) {
        HashSet<String> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();
        for(int i=0;i<=s.length()-10;i++){
            String substring=s.substring(i, i + 10);
            if(seen.contains(substring)){
                res.add(substring);
            }else{
                seen.add(substring);
            }
        }
        return new ArrayList<String>(res);
    }

//    public List<String> findRepeatedDnaSequences(String s) {
//        HashMap<String,Integer> map = new HashMap<>();
//        for(int i=0;i<=s.length()-10;i++){
//            String substring=s.substring(i, i + 10);
//            if(map.containsKey(substring)){
//                map.put(substring, map.get(substring) + 1);
//            }else {
//                map.put(substring, 1);
//            }
//        }
//        ArrayList<String> list = new ArrayList<>();
//        for(String str:map.keySet()) {
//            if(map.get(str)>1){
//                list.add(str);
//            }
//        }
//        return list;
//    }
}