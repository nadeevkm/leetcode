class Solution {

    int[][] dirs = new int[][] { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        dfs(image, sr, sc, image[sr][sc], color);
        return image;
    }

    public void dfs(int[][] image, int r, int c, int target, int color) {
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length || image[r][c] != target || image[r][c] == color) {
            return;
        }
        image[r][c] = color;
        for (var d : dirs) {
            dfs(image, r + d[0], c + d[1], target, color);
        }
    }
}
