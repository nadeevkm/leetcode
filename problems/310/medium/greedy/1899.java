class Solution1 { // emulate and run optimal choices
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int[] prev = new int[] { -1, -1, -1 };
        for (var tripl : triplets) {
            if (valid(tripl, target)) {
                merge(prev, tripl);
                if (isSame(tripl, target)) {
                    return true;
                }
                prev = tripl;
            }
        }
        return false;
    }

    private void merge(int[] prev, int[] triplet) {
        triplet[0] = Math.max(prev[0], triplet[0]);
        triplet[1] = Math.max(prev[1], triplet[1]);
        triplet[2] = Math.max(prev[2], triplet[2]);
    }

    private boolean valid(int[] cand, int[] target) {
        return (cand[0] == target[0] || cand[1] == target[1] || cand[2] == target[2]) &&
                (cand[0] <= target[0] && cand[1] <= target[1] && cand[2] <= target[2]);
    }

    private boolean isSame(int[] cand, int[] target) {
        return cand[0] == target[0] && cand[1] == target[1] && cand[2] == target[2];
    }
}

class Solution2 {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // check is it possible to achieve target[0],target[1], target[2] value at index 0,1,2 accoringly in any triplet
        for (int i = 0; i < 3; i++){
            var indCheck = checkInd(i, triplets, target);
            if (!indCheck){
                return false;
            }
        }
        return true;
    }

    private boolean checkInd(int targetInd,int[][] triplets, int[] target){
        for (var tr : triplets){
            if (tr[targetInd] == target[targetInd] &&
                    tr[0] <= target[0] && tr[1] <= target[1] && tr[2] <= target[2]){
                return true;
            }
        }
        return false;
    }
}