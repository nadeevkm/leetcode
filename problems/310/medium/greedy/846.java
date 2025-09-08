import java.util.*;

class Solution {

    // {1,2,3,3,4,4,5,6}
    // 4
    // 1 - 1, 2
    // 2 - 1,-1
    // 3 -
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }
        int groupCount = hand.length / groupSize;
        Arrays.sort(hand);
        Map<Integer, int[]> map = new HashMap<>(); // 0 - number count, 1 - next number in hand[]
        map.put(hand[0], new int[]{1, -1});
        int prev = hand[0];
        for (int i = 1; i < hand.length; i++) {
            var n = hand[i];
            var pair = map.computeIfAbsent(hand[i], k -> new int[]{0, -1});
            pair[0]++;
            map.get(prev)[1] = n;
            prev = n;
        }
        Integer nextGroupStart = hand[0];
        while (groupCount > 0) {
            int start = nextGroupStart;
            nextGroupStart = null;
            for (int n = start; n < start + groupSize; n++) {
                var cand = map.get(n);
                if (cand == null || cand[0] == 0) {
                    return false;
                }
                cand[0]--;
                if (cand[0] > 0 && nextGroupStart == null) {
                    nextGroupStart = n;
                }
            }
            if (nextGroupStart == null) {
                nextGroupStart = map.get(start + groupSize - 1)[1];
            }
            groupCount--;
        }
        return true;
    }
}


class SolutionConcise { // just always try to construct group starting from the minimum available num, use Heap for that
    public boolean isNStraightHand(int[] hand, int groupSize) {
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> pq = new PriorityQueue<>();
        for (int n : hand) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
                pq.add(n);
            }
        }
        while (!pq.isEmpty()) {
            int smallest = pq.peek(); // !! - here peek, not poll
            for (int i = smallest; i < smallest + groupSize; i++) {
                var ic = map.get(i);
                if (ic == null || ic == 0) {
                    return false;
                }
                int updCnt = ic - 1;
                if (updCnt == 0) {
                    if (pq.peek() != i) { // otherwise we are creating a hole in potentially consequitive numbers
                        return false;     // means that peak count is > 0, and we will need to construct at least one more straight consequence starting from peek, but we are already run out of next subsequent numbers
                    }
                    pq.poll();
                }
                map.put(i, updCnt);
            }
        }
        return true;
    }
}

class SolutionRepetition {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0){
            return false;
        }
        var freq = new TreeMap<Integer, Integer>();
        for (var n : hand){
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        while (freq.size() != 0){
            int nextMin = freq.keySet().iterator().next();
            for (int required = nextMin; required < nextMin + groupSize; required++){
                if (!freq.containsKey(required)){
                    return false;
                } else {
                    int updCount = freq.get(required) - 1;
                    if (updCount == 0){
                        freq.remove(required);
                    } else {
                        freq.put(required, updCount);
                    }
                }
            }
        }
        return true;
    }
}

class Check {
    public static void main(String[] args) {
        var res = new Solution().isNStraightHand(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4);
        var p = 0;
    }
}