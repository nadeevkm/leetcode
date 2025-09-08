class SolutionUnionFind {
    public int countComponents(int n, int[][] edges) {
        int[] roots = new int[n];
        for (int i = 0; i < n; i++){
            roots[i] = i;
        }
        int[] ranks = new int[n];

        int count = n;
        for (var e : edges){
            count -= union(roots, ranks, e[0], e[1]);
        }
        return count;
    }

    private int find(int[] roots, int val){
        int root = roots[val];
        while (root != val){
            int prevVal = val;  //1
            val = root;
            root = roots[root];
            roots[prevVal] = root; //2  (1) and (2) optimizations for path compression
        }
        return root;
    }

    private int union(int[] roots, int[] ranks, int val1, int val2){
        int root1 = find(roots, val1);
        int root2 = find(roots, val2);

        if (root1 == root2){
            return 0; // already connected, no need to do that again and treat it as a new found join
        }

        if (ranks[root1] > ranks[root2]){
            roots[root2] = root1;
            ranks[root1] += ranks[root2];
        } else {
            roots[root1] = root2;
            ranks[root2] += ranks[root1];
        }
        return 1;
    }
}