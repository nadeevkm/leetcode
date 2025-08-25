class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int lp = 0;
        int rp = n - 1;
        int maxArea = Math.min(height[lp], height[rp])*(rp - lp);
        while (lp < rp){
            if (height[lp] <= height[rp]){
                lp++;
            } else {
                rp--;
            }
            int currMaxArea = Math.min(height[lp], height[rp])*(rp - lp);
            maxArea = Math.max(maxArea, currMaxArea);
        }
        return maxArea;
    }
}
