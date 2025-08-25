import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    // without heap, use idea of intervals intersection, shoot for intersection when next interval is not fit into current
    // cause basically we need to shoot every ballon and this next one we can only shoot with different arrow from arrow to shot interval which ends before (its end is end of current intersection)
    public int findMinArrowShots1(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(interv -> interv[0]));
//        int intersectionStart = 0;  --> actually do not need it
        int intersectionEnd = Integer.MAX_VALUE;
        int arr = 0;
        for (var interv : points) {
            if (interv[0] > intersectionEnd){
                arr++;
//                intersectionStart = interv[0];
                intersectionEnd = interv[1];
            } else {
                intersectionEnd = Math.min(intersectionEnd, interv[1]);
//                intersectionStart = Math.max(intersectionStart, interv[0]); / always interv[0] as we sorted them
            }
        }
        arr++;
        return arr;
    }
    // [[10,16],[2,8],[1,6],[7,12]]
    //               /
    // 1,6 2,8 7,12 10,16
    // ends - 10
    // options - 2 2 1 1
    // [[1,2],[2,3],[3,4],[4,5]]
    //                     /
    // [1,2],[2,3],[3,4],[4,5]
    // ends - 4 5
    // options - 2 2 2 1
    public int findMinArrowShots(int[][] points) {

        Arrays.sort(points, Comparator.comparingInt(interv -> interv[0]));
        PriorityQueue<Integer> ends = new PriorityQueue<>();
        int arr = 0;
        for (var interv : points) {
            if (ends.size() != 0 && interv[0] > ends.peek()){
                ends.clear();
                arr++;
            }
            ends.add(interv[1]);
        }

        if(ends.size() != 0){
            arr++;
        }

        return arr;
    }
}

class Check{
    public static void main(String[] args) {
        int res = new Solution().findMinArrowShots(new int[][]{{0,9},{1,8},{7,8},{1,6},{9,16},{7,13},{7,10},{6,11},{6,9},{9,13}});
        int pp = 2;
    }
}
