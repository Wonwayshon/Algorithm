import java.util.TreeMap;

class MapSum {
    private class Node{
        public TreeMap<Character, Node> next;
        public int value;

        public Node(int value){
            next = new TreeMap<>();
            this.value = value;
        }

        public Node(){
            this(0);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int val) {
        Node cur = root;
        for(int i=0;i<key.length();i++){
            char ch = key.charAt(i);
            if(cur.next.get(ch)==null){
                cur.next.put(ch, new Node());
            }
            cur = cur.next.get(ch);
        }
        cur.value = val;
    }

    public int sum(String prefix) {
        Node cur = root;
        for(int i=0;i<prefix.length();i++){
            char ch = prefix.charAt(i);
            if(cur.next.get(ch)==null){
                return 0;
            }
            cur = cur.next.get(ch);
        }
        return sum(cur);
    }

    //递归方法 对以node为根节点的Trie求和
    public int sum(Node node){
        int result = node.value;
        for(char nextChar:node.next.keySet()){
            result+=sum(node.next.get(nextChar));
        }
        return result;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
