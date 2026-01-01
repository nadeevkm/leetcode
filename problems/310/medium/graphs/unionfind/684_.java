class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int size = edges.length + 1;
        int[] roots = new int[size];
        for (int i = 0; i < size; i++){
            roots[i] = i;
        }
        int[] ranks = new int[size];

        for (var ed : edges){
            var root0 = find(ed[0], roots);
            var root1 = find(ed[1], roots);

            if (root0 == root1) {
                return ed;
            }

            //union
            if (ranks[root0] > ranks[root1]){
                roots[root1] = root0;
                ranks[root0] += ranks[root1];
            } else {
                roots[root0] = root1;
                ranks[root1] += ranks[root0];
            }
        }
        return new int[2];
    }

    public int find (int val, int[] roots){
        var root = roots[val];
        if (root != val){
            root = find(root, roots);
            roots[val] = root;
        }
        return root;
    }
}