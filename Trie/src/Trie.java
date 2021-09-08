import java.util.TreeMap;

public class Trie {
    private class Node {
        public TreeMap<Character, Node> next;
        public boolean isWord;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    //向Trie中添加单词
    public void add(String word){
        Node cur = root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(cur.next.get(ch)==null){
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }
        if(!cur.isWord) {
            cur.isWord=true;
            size++;
        }
    }

    //在Trie中查找单词
    public boolean contains(String word){
        Node cur=root;
        for(int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if(cur.next.get(ch)==null){
                return false;
            }
            cur = cur.next.get(ch);
        }
        return cur.isWord;
    }

    //在Trie中查找单词前缀
    public boolean isPrefix(String prefix){
        Node cur=root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(cur.next.get(ch)==null){
                return false;
            }
            cur = cur.next.get(ch);
        }
        return true;
    }
}
