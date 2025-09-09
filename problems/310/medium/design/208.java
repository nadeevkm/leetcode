class Trie {

    Node root;

    public Trie() {
        root = new Node();
    }
    
    public void insert(String word) {
        var node = root;
        for (char ch : word.toCharArray()){
            if (node.childs[ch - 'a'] == null){
                node.childs[ch - 'a'] = new Node();
            }
            node = node.childs[ch - 'a'];
        }
        node.isWord = true;
    }
    
    public boolean search(String word) {
        var node = findNode(word);
        return node != null && node.isWord;
    }
    
    public boolean startsWith(String prefix) {
        var node = findNode(prefix);
        return node != null;
    }

    private Node findNode(String s) {
        var node = root;
        for (char ch : s.toCharArray()){
            if (node == null){
                return null;
            }
            node = node.childs[ch - 'a'];
        }
        return node;
    }

    class Node{
        Node[] childs = new Node[26];
        boolean isWord = false;
    }
}