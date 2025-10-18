import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[][] abc = new int[26][2];
        Arrays.setAll(abc, (i) -> new int[]{-1, -1});
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (abc[ch - 'a'][0] == -1) {
                abc[ch - 'a'][0] = i;
            }
            abc[ch - 'a'][1] = i;
        }
        Arrays.sort(abc, (n1, n2) -> n1[0] - n2[0]); // intervals are alredy sorted, so actually no need to sort them, use LinkedHashMap (loop with 26 entries) or use original order during for loop using string chars order (loop with s.length() entries)
        int[] currInt = null;
        List<Integer> res = new LinkedList<>();
        for (int[] interv : abc) {   // for (var ch : abc.toCharArray) { var interv = abc[ch-'a']; ...
            if (interv[0] == -1) {
                continue;
            }
            if (currInt == null) {
                currInt = interv;
            } else {
                if (interv[0] <= currInt[1]) {
                    currInt[1] = Math.max(currInt[1], interv[1]);
                } else {
                    res.add(currInt[1] - currInt[0] + 1);
                    currInt = interv;
                }
            }
        }
        res.add(currInt[1] - currInt[0] + 1);
        return res;
    }
}

class SolutionOpt { // O(n), intervals are already sorted, so actually no need to sort them, use LinkedHashMap (loop with 26 entries) or use original order during for loop using string chars (loop with s.length() entries)
    public List<Integer> partitionLabels(String s) {
        LinkedHashMap<Character, int[]> abc = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (abc.containsKey(ch)) {
                abc.get(ch)[1] = i;
            } else {
                abc.put(ch, new int[]{i, i});
            }
        }
        int[] currInt = null;
        List<Integer> res = new LinkedList<>();
        for (var interv : abc.values()) {
            if (currInt == null) {
                currInt = interv;
            } else {
                if (interv[0] <= currInt[1]) {
                    currInt[1] = Math.max(currInt[1], interv[1]);
                } else {
                    res.add(currInt[1] - currInt[0] + 1);
                    currInt = interv;
                }
            }
        }
        res.add(currInt[1] - currInt[0] + 1);
        return res;
    }
}

class SolutionWithMapAndSet {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s.toCharArray()){
            int fr = freq.getOrDefault(c, 0);
            freq.put(c, fr + 1);
        }

        List<Integer> res = new LinkedList<>();

        int charsLeft = 0;
        Set<Character> partChars = new HashSet<>();
        int partStart = 0;
        for (int i = 0; i <= s.length() - 1; i++){
            char currChar = s.charAt(i);
            if (!partChars.contains(currChar)){
                charsLeft += freq.get(currChar);
                partChars.add(currChar);
            }

            charsLeft--;

            if (charsLeft == 0){
                partChars.clear();
                res.add(i - partStart + 1);
                partStart = i + 1;
            }
        }
        return res;
    }
}

class SolutionGreedy { // correct, but messy solution
     public List<Integer> partitionLabels(String s) {
        Integer[] abc = new Integer[26];
        Deque<Integer> partitions = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (abc[ch - 'a'] == null) { // first occurence
                abc[ch - 'a'] = i + 1;
                partitions.add(i + 1);
            } else {
                while (!partitions.isEmpty() && partitions.peekLast() >= abc[ch - 'a']) {
                    partitions.removeLast();
                }
                partitions.add(i + 1);
                abc[ch - 'a'] = i + 1;
            }
        }
        Integer prev = 0;
        List<Integer> res = new LinkedList<>();
        while (!partitions.isEmpty()){
            var curr = partitions.removeFirst();
            res.add(curr - prev);
            prev = curr;
        }
        return res;
    }
}

class SolutionGreedyOptimalWithMap {
    public List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastOccurence = new HashMap<>();
        for (int i = 0; i <= s.length() - 1; i++){
            char currChar = s.charAt(i);
            int lastOcc = Math.max(i, lastOccurence.getOrDefault(currChar, -1));
            lastOccurence.put(currChar, lastOcc);
        }

        List<Integer> res = new LinkedList<>();

        int partStart = 0;
        int partEnd = 0;
        for (int i = 0; i <= s.length() - 1; i++){
            char currChar = s.charAt(i);
            partEnd = Math.max(partEnd, lastOccurence.get(currChar));
            if (partEnd == i){
                res.add(i - partStart + 1);
                partStart = i + 1;
                partEnd = partStart;
            }
        }
        return res;
    }
}

class SolutionGreedyOptimal {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); ++i){
            last[s.charAt(i) - 'a'] = i;
        }
        int partEnd = 0;
        int partSize = 0;
        List<Integer> ans = new LinkedList<>();
        for (int i = 0; i < s.length(); ++i) {
            partSize++;
            partEnd = Math.max(partEnd, last[s.charAt(i) - 'a']);
            if (i == partEnd) {
                ans.add(partSize);
                partSize = 0;
            }
        }
        return ans;
    }
}

class Check {
    public static void main(String[] args) {
        var res = new SolutionGreedy().partitionLabels("ababcbacadefegdehijhklij");
        var p = 0;
    }
}