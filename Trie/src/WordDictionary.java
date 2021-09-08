import java.util.TreeMap;

//LeetCode211
class WordDictionary {
    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(cur.next.get(ch)==null){
                cur.next.put(ch, new Node());
            }
            cur=cur.next.get(ch);
        }
        if(!cur.isWord){
            cur.isWord = true;
        }
    }

    public boolean search(String word) {
        return search(root, word, 0);
    }

    //递归方法 在以node为根节点的Trie中搜索word字符串索引为index位置的字母
    private boolean search(Node node,String word,int index){
        if(index==word.length()){
            return node.isWord;
        }
        char ch = word.charAt(index);
        if(ch!='.'){
            if(node.next.get(ch)==null){
                return false;
            }else{
                return search(node.next.get(ch),word,index+1);
            }
        }else{
            for(char nextChar:node.next.keySet()){
                if(search(node.next.get(nextChar),word,index+1)){
                    return true;
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */