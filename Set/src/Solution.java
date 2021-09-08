import java.util.TreeSet;
//804. 唯一摩尔斯密码词
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] codes={".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        TreeSet<String> set=new TreeSet<>();
        for(String word:words){
            StringBuilder temp=new StringBuilder();
            for(int i=0;i<word.length();i++){
                temp.append(codes[word.charAt(i)-'a']);
            }
            set.add(temp.toString());
        }
        return set.size();
    }
}
