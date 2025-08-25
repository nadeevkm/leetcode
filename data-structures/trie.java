class TrieNode {

    boolean endOfWord;
    TrieNode[] childs;

    TrieNode() {
        endOfWord = false;
        childs = new TrieNode[26];
    }
}

class Trie {

    TrieNode rootNode;

    public Trie() {
        rootNode = new TrieNode();
    }

    public void insert(String word) {
        if (word.length() == 0) {
            return;
        }
        var node = rootNode;
        for (char ch : word.toCharArray()) {
            if (node.childs[ch - 'a'] != null) {
                node = node.childs[ch - 'a'];
            } else {
                var childNode = new TrieNode();
                node.childs[ch - 'a'] = childNode;
                node = childNode;
            }
        }
        node.endOfWord = true;
    }

    public boolean search(String word) {
        var node = findNode(word);
        return node != null && node.endOfWord;
    }

    public boolean startsWith(String prefix) {
        return findNode(prefix) != null;
    }

    private TrieNode findNode(String s) {
        var node = rootNode;
        for (char ch : s.toCharArray()) {
            if (node.childs[ch - 'a'] != null) {
                node = node.childs[ch - 'a'];
            } else {
                return null;
            }
        }
        return node;
    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */