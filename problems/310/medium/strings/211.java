class WordDictionary {

    Node root;

    class Node {
        Node[] childs = new Node[26];
        boolean isEnd = false;
    }

    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        var node = root;
        for (char ch : word.toCharArray()) {
            if (node.childs[ch - 'a'] == null){
                node.childs[ch - 'a'] = new Node();
            }
            node = node.childs[ch - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        return dfs(word.toCharArray(), 0, root);
    }

    private boolean dfs(char[] chars, int ind, Node current){
        if (current == null){
            return false;
        }
        if (ind == chars.length){
            return current.isEnd;
        }
        if (emptyNode(current)){
            return false;
        }

        if (chars[ind] != '.'){
            return dfs(chars, ind + 1, current.childs[ch - 'a']);
        } else {
           for (var node : current.childs) {
               var res = dfs(chars, ind + 1, node);
               if (res){
                   return true;
               }
           }
           return false;
        }
    }

    private boolean emptyNode(Node node){
        for (var n : node.nodes){
            if (n != null){
                return false;
            }
        }
        return true;
    }
}