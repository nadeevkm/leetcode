class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] parents = new int[n];

        for (int i = 0; i < n; i++){
            parents[i] = i;
        }

        for (var edge : edges){
            int parent0 = find(parents,edge[0]);
            int parent1 = find(parents, edge[1]);

            if (parent0 == parent1){ // found a cycle
                return false;
            }

            parents[parent0] = parent1;
            n--;
        }

        return n == 1; // !! - not 0, should be one tree left, cause initially we create n separate union-sets/trees
    }

    public int find(int[] parents, int v){
        int par = parents[v];
        if (par != v){
            par = find(parents, par);
            parents[v] = par;
        }
        return par;
    }
}
