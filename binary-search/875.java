import java.util.Arrays;

class Solution {
    public int minEatingSpeedClosedInterval(int[] piles, int h) { // open interval
        int r = max(piles); // max k
        int l = 1; // min k

        while (l <= r){
            int m = l + (r - l)/2;
            if (canEat(piles, h, m)){
                r = m - 1; // than try to reduce m (k), but m still can be the answer in the end
            } else {
                l = m + 1; // m won't be an answer than
            }
        }
        return l;

        /* also can be done with intermediate res

            int res = r;
            while (l <= r){
                int m = l + (r - l)/2;
                if (canEat(piles, h, m)){
                    res = Math.min(res, m);
                    r = m - 1; // than try to reduce m (k), but m still can be the answer in the end
                } else {
                    l = m + 1; // m won't be an answer than
                }
            }
            return res;
         */
    }

    public int minEatingSpeedOpenInterval(int[] piles, int h) { // open interval
        int r = max(piles); // max k
        int l = 1; // min k

        while (l < r){
            int m = l + (r - l)/2;
            if (canEat(piles, h, m)){ // than try to reduce m (k)
                r = m; // it still can be m
            } else {
                l = m + 1; // m won't be an answer than
            }
        }
        return l;
    }

    public boolean canEat(int[] piles, int h, int k){
        int totalH = 0;
        for (var pile : piles){
            totalH = totalH + (int)Math.ceil((double)pile/k);
        }
        return totalH <= h;
    }

    public int max(int[] piles){
        int max = 0;
        for (var pile : piles){
            max = Math.max(max, pile);
        }
        return max;
    }
}

