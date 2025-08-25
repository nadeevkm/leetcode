import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int intervals = 0;

        int[] freq = new int[26];
        for (var ch : tasks) {
            freq[ch - 'A']++;
        }
        Queue<Integer> priorityQ = new PriorityQueue<>((n1, n2) -> n2 - n1);
        for (var fr : freq) {
            if (fr == 0) {
                continue;
            }
            priorityQ.add(fr);
        }

        Queue<int[]> coolDownQ = new LinkedList<>(); // tasks, which cannot be run currently, [0] - freq(number of remaining), [1] - current cpu interval sequence
        while (!priorityQ.isEmpty() || !coolDownQ.isEmpty()) {
            while (priorityQ.isEmpty() && !coolDownQ.isEmpty()){
                if (intervals - coolDownQ.peek()[1] < n){
                    intervals++; // idle interval in order to use same task again
                } else {
                    priorityQ.add(coolDownQ.poll()[0]);
                }
            }
            var mostFr = priorityQ.poll();
            intervals++;
            mostFr--;
            if (mostFr > 0) {
                coolDownQ.add(new int[]{mostFr, intervals});
            }
            while (!coolDownQ.isEmpty() && (intervals - coolDownQ.peek()[1] >= n)){
                priorityQ.add(coolDownQ.poll()[0]);
            }
        }

        return intervals;
    }
}


class SolutionOptimizedWhile { // same as previous, bit optimized 'while'
    public int leastInterval(char[] tasks, int n) {
        int intervals = 0;

        int[] freq = new int[26];
        for (var ch : tasks) {
            freq[ch - 'A']++;
        }
        Queue<Integer> priorityQ = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
        for (var fr : freq) {
            if (fr == 0) {
                continue;
            }
            priorityQ.add(fr);
        }

        Queue<int[]> coolDownQ = new LinkedList<>(); // tasks, which cannot be run currently, [0] - freq(number of remaining), [1] - cpu interval when we can use it again
        while (!priorityQ.isEmpty() || !coolDownQ.isEmpty()) {
            intervals++;
            if (!priorityQ.isEmpty()){  // so we either run a task or spent this interval as an idle one
                var mostFr = priorityQ.poll() - 1;
                if (mostFr > 0) {
                    coolDownQ.add(new int[]{mostFr, intervals + n});
                }
            }
            while (!coolDownQ.isEmpty() && coolDownQ.peek()[1] <= intervals){ // add to possible tasks all cooled down task
                priorityQ.add(coolDownQ.poll()[0]);
            }
        }

        return intervals;
    }
}

class Check {
    public static void main(String[] args) {
//        var r1 = new Solution().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2);
//        var r2 = new Solution().leastInterval(new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'}, 7);
        var r3 = new Solution().leastInterval(new char[]{'A', 'A', 'A', 'A', 'B', 'B', 'B'}, 1);
        var rr = 0;
    }
}
