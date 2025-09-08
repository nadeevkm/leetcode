import java.util.*;

class Solution { // with heap
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int n : nums) {
            int freq = freqs.getOrDefault(n, 0);
            freqs.put(n, freq + 1);
        }

        var pq = new PriorityQueue<int[]>(k, (n1, n2) -> n1[1] - n2[1]); // actually this K sets ony initial capacity, so we need to maintain it manually
        // more efficient way is to use freq map right here -> new PriorityQueue<int[]>(k, (n1, n2) -> freqs.get(n2) - freqs.get(n1))
        for (var entry : freqs.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
            if (pq.size() > k) {
                pq.poll(); // maintain capacity manually, that's why we need 'the less frequent element first' heap
            }
        }

        int[] ans = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            ans[i] = pq.poll()[0];
        }
        return ans;
    }
}

class SolutionQuickSelect { // with quick select
    Random random = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqs = new HashMap<>();
        for (int n : nums) {
            int freq = freqs.getOrDefault(n, 0);
            freqs.put(n, freq + 1);
        }

        var uniqNums = new int[freqs.size()];
        int i = 0;
        for (int n : freqs.keySet()) {
            uniqNums[i] = n;
            i++;
        }

        var index = uniqNums.length - k;
        quickSelect(0, uniqNums.length - 1, uniqNums, index, freqs);

        return Arrays.copyOfRange(uniqNums, index, uniqNums.length);
    }

    public int quickSelect(int start, int end, int[] nums, int index, Map<Integer, Integer> freqs) {
        if (start == end) {
            return nums[start];
        }
        int pi = partition(start, end, nums, freqs);
        if (pi == index) {
            return nums[pi];
        } else if (index > pi) {
            return quickSelect(pi + 1, end, nums, index, freqs);
        } else {
            return quickSelect(start, pi - 1, nums, index, freqs);
        }
    }

    public int partition(int start, int end, int[] nums, Map<Integer, Integer> freqs) {
        int pi = start + random.nextInt(end - start);
        swap(end, pi, nums);
        pi = start;
        for (int i = start; i <= end - 1; i++) {
            if (freqs.get(nums[i]) < freqs.get(nums[end])) {
                swap(i, pi, nums);
                pi++;
            }
        }
        swap(pi, end, nums);
        return pi;
    }

    public void swap(int i, int j, int[] nums) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }
}


class SolutionHeapRepetit {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (var n : nums) {
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> freq.get(i1) - freq.get(i2));
        for (var uniq : freq.keySet()) {
            pq.add(uniq);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        var res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}


class SolutionQuickSelectRepetit {
    Random rand = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (var n : nums){
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }
        int[] unique = new int[freq.size()];
        int i = 0;
        for(var un : freq.keySet()){
            unique[i] = un;
            i++;
        }
        int targetInd = unique.length - k;
        quickSelect(unique,0, unique.length - 1, targetInd, freq);
        return Arrays.copyOfRange(unique, targetInd, unique.length);
    }

    private int quickSelect(int[] unique, int start, int end, int target, Map<Integer, Integer> freq){
        int p = partition(unique, start, end, target, freq);
        if (p > target){
            return quickSelect(unique, start, p - 1, target, freq);
        } else if (p < target) {
            return quickSelect(unique, p + 1, end, target, freq);
        } else {
            return p;
        }
    }

    private int partition(int[] unique, int start, int end, int target, Map<Integer, Integer> freq){
        int randInd = start + rand.nextInt(end - start + 1);
        swap(unique, randInd, end);
        int piv = start;
        for (int i = start; i < end; i++){
            if (freq.get(unique[i]) < freq.get(unique[end])){
                swap(unique, i, piv);
                piv++;
            }
        }
        swap(unique, piv, end);
        return piv;
    }

    private void swap(int[] nums, int i1, int i2){
        var tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}




class Check {
    public static void main(String[] args) {
//        var r = new Solution().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        var r1 = new Solution().topKFrequent(new int[]{1, 1, 1, 2, 2, 3, 6, 7, 9, 9}, 2);
        var r2 = new SolutionQuickSelect().topKFrequent(new int[]{1, 1, 1, 2, 2, 3, 6, 7, 9, 9}, 2);
        var rr = 0;
    }
}