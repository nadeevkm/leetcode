import java.util.*;

class Solution {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> res = new ArrayList<>();
        var pq = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < Math.min(k, nums1.length); i++){
            pq.add(new int[]{i, 0, nums1[i] + nums2[0]});
        }

        while(!pq.isEmpty() && k > 0){
            var minPair = pq.remove();
            res.add(Arrays.asList(nums1[minPair[0]], nums2[minPair[1]]));
            k--;

            if (minPair[1] + 1 < nums2.length){
                pq.add(
                        new int[]{
                                minPair[0], minPair[1] + 1, nums1[minPair[0]] + nums2[minPair[1] + 1]
                        }
                );
            }
        }
        return res;
    }

    /*
        treat input and sums as a matrix
        [-10,-4,0,0,6]
        [3,5,6,7,8,100]

        as   3, 5, 6, 7, 8, 100
        -10  -7 -5 ...
        -4   -1  ..
         0
         -
         6                  106


         Here's a visualization of a possible state:

            # # # # # ? . .
            # # # ? . . . .
            # ? . . . . . .   "#" means pair already in the output
            # ? . . . . . .   "?" means pair currently in the queue
            # ? . . . . . .
            ? . . . . . . .
            . . . . . . . .
     */
    public List<List<Integer>> kSmallestPairsMatrix(int[] nums1, int[] nums2, int k){
        PriorityQueue<Sum> pQ = new PriorityQueue<>((a, b) -> a.val - b.val);

        pQ.add(new Sum(0,0, nums1, nums2));
        List<List<Integer>> resList = new ArrayList<>();

        while (!pQ.isEmpty() && resList.size() < k){
            var minSum = pQ.remove();
            resList.add(Arrays.asList(nums1[minSum.ind1], nums2[minSum.ind2]));

            if (minSum.ind2 + 1 < nums2.length){ // add next in the row
                pQ.add(new Sum(minSum.ind1, minSum.ind2 + 1, nums1, nums2));
            }
            if (minSum.ind2 == 0 && minSum.ind1 + 1 < nums1.length){ // add below one as well
                pQ.add(new Sum(minSum.ind1 + 1, minSum.ind2, nums1, nums2));
            }
        }
        return resList;
    }

    class Sum{
        int val;
        int ind1;
        int ind2;

        public Sum(int ind1, int ind2, int[] nums1, int[] nums2){
            this.val = nums1[ind1] + nums2[ind2];
            this.ind1 = ind1;
            this.ind2 = ind2;
        }
    }


    //            n1p1     n1p2
    //  [1,   7,   11]
    //
    //       n2p1
    //                    n2p2
    //  [2,   4,   6]
    //
    //  [3,   3,   1]
    //
    // 13 - 15
    // [1,2] [1,4] [1,6]  [7,2] [7,4] [11,2] [7,6] [11, 4] [11, 6]


    // Не рабочее решение!!
    public List<List<Integer>> kSmallestPairs_falseAttempt(int[] nums1, int[] nums2, int k) {
        int[] progress = new int[nums1.length];
        List<List<Integer>> resList = new ArrayList<>();

        int n1p1 = 0;
        int n1p2 = 1;

        while (n1p1 < nums1.length && resList.size() < k){
            int n2p1 = progress[n1p1];
            int sum1 = nums1[n1p1] + nums2[n2p1];
            if (n1p2 < nums1.length){
                int n2p2 = progress[n1p2];
                int sum2 = nums1[n1p2] + nums2[n2p2];
                if (sum1 < sum2){
                    // choose sum1, add candidates pair 1 to list;
                    List<Integer> locSum = new ArrayList<>();
                    locSum.add(nums1[n1p1]);
                    locSum.add(nums2[n2p1]);
                    resList.add(locSum);
                    progress[n1p1]++;
                    if (progress[n1p1] == nums2.length){
                        n1p1++;
                        n1p2++;
                    }
                } else {
                    // choose sum1, add candidates2 to list
                    List<Integer> locSum = new ArrayList<>();
                    locSum.add(nums1[n1p2]);
                    locSum.add(nums1[n1p2]);
                    resList.add(locSum);
                    progress[n1p2]++;
                    if (progress[n1p2] == nums2.length){
                        n1p2++;
                    }
                }
            } else {
                // add cand1 to list
                List<Integer> locSum = new ArrayList<>();
                locSum.add(nums1[n1p1]);
                locSum.add(nums2[n2p1]);
                resList.add(locSum);
                progress[n1p1]++;
                if (progress[n1p1] == nums2.length){
                    n1p1++;
                }
            }
        }

        return resList;
    }
}


class SolutionRepetit {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new LinkedList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (i1, i2) -> nums1[i1[0]] + nums2[i1[1]] - nums1[i2[0]] - nums2[i2[1]]);
        pq.add(new int[]{0, 0});
        var visited = new boolean[nums1.length][nums2.length];
        visited[0][0] = true;
        while (res.size() != k) {
            var curr = pq.poll();
            int row = curr[0];
            int col = curr[1];
            res.add(Arrays.asList(nums1[row], nums2[col]));
            if (row + 1 < nums1.length && !visited[row + 1][col]) {
                pq.add(new int[]{row + 1, col});
                visited[row + 1][col] = true;
            }
            if (col + 1 < nums2.length && !visited[row][col + 1]) {
                pq.add(new int[]{row, col + 1});
                visited[row][col + 1] = true;
            }
        }
        return res;
    }
}

class SolutionMemoryOptimised { // don't need visit actually, just add next within the row (next col), and below only in special cases (when col == 0)
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new LinkedList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (i1, i2) -> nums1[i1[0]] + nums2[i1[1]] - nums1[i2[0]] - nums2[i2[1]]);
        pq.add(new int[]{0, 0});
        while (res.size() != k) {
            var curr = pq.poll();
            int row = curr[0];
            int col = curr[1];
            res.add(Arrays.asList(nums1[row], nums2[col]));
            if (col + 1 < nums2.length) {
                pq.add(new int[]{row, col + 1});
            }
            if (col == 0 && row + 1 < nums1.length) {
                pq.add(new int[]{row + 1, col});
            }
        }
        return res;
    }
}


class Check {
    public static void main(String[] args) {
        var sol = new Solution();
        var pp = sol.kSmallestPairs(new int[]{-10,-4,0,0,6}, new int[] {3,5,6,7,8,100}, 10);
    }
}