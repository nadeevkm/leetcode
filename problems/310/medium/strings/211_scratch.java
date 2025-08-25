import java.util.LinkedList;
import java.util.List;

class TrieNode {

    boolean endOfWord;
    TrieNode[] childs;
    List<TrieNode> allChilds;

    TrieNode() {
        endOfWord = false;
        childs = new TrieNode[26];
        allChilds = new LinkedList<>();
    }
}

class WordDictionary {

    TrieNode rootNode;

    public WordDictionary() {
        rootNode = new TrieNode();
    }

    public void addWord(String word) {
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
                node.allChilds.add(childNode);
                node = childNode;
            }
        }
        node.endOfWord = true;
    }

    public boolean search(String word) {
        return backtrack(rootNode, word,0);
    }

    private boolean backtrack(TrieNode node, String s, int i){
        if (i == s.length()){
            return node.endOfWord;
        }

        char ch = s.charAt(i);
        if (ch != '.'){
            if(node.childs[ch - 'a'] == null){
                return false;
            } else {
                return backtrack(node.childs[ch - 'a'], s , i + 1);
            }
        } else {
            boolean res = false;
            for (var child : node.allChilds){ // or iterate right through `childs`, skipping nulls
                res = res || backtrack(child, s, i + 1);
            }
            return res;
        }
    }
}