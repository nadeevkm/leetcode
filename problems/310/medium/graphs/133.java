
class SolutionBfs {
    public Node cloneGraph(Node node) {
        
        if (node == null){
            return null; 
        }
        
        Map<Node, Node> origToClone = new HashMap<>();
        var cloneNode = new Node(node.val);
        origToClone.put(node, cloneNode);

        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while(!q.isEmpty()){

            var cNode = q.poll();
            var clone = origToClone.get(cNode);

            for (var neig : cNode.neighbors){
                if (origToClone.containsKey(neig)){
                    clone.neighbors.add(origToClone.get(neig));
                } else {
                    var neigClone = new Node(neig.val);
                    clone.neighbors.add(neigClone);
                    origToClone.put(neig, neigClone);
                    q.add(neig);
                }
            }
        }

        return cloneNode;
    }
}

class SolutionRepetit {
    public Node cloneGraph(Node node) {
        if (node == null){
            return null;
        }
        return bfs(node);
    }

    private Node bfs(Node node){
        Map<Node, Node> reached = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        reached.put(node, new Node(node.val));
        while(!q.isEmpty()){
            var curr = q.poll();
            var clone = reached.get(curr);
            for (var neigh : curr.neighbors){
                if (reached.containsKey(neigh)){
                    clone.neighbors.add(reached.get(neigh));
                    continue;
                }
                q.add(neigh);
                var clonedNeight = new Node(neigh.val);
                reached.put(neigh, clonedNeight);
                clone.neighbors.add(clonedNeight);
            }
        }
        return reached.get(node);
    }
}