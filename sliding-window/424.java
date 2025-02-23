import java.util.HashMap;
import java.util.Map;

class Solution { // use left pointer-based sliding window code
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> counts = new HashMap<>();
        int winSt = 0;
        // window is a search window, i.e. potential, and res is confirmed length, which is search window - 1, as we always increase search window as soon as we find requred conditions
        int winSize = 1;
        while (winSt + winSize - 1 < s.length()) { // exit when winSt + winSize - 1 > s.length();

            char addedChar = s.charAt(winSt + winSize - 1);
            int addedCharCount = counts.getOrDefault(addedChar, 0) + 1;
            counts.put(addedChar, addedCharCount);

            if (winSize <= k || hasCount(counts, winSize - k)) {
                winSize++;
            } else {
                char removedChar = s.charAt(winSt);
                int removedCharCount = counts.get(removedChar) - 1;
                counts.put(removedChar, removedCharCount);

                winSt++;
            }
        }
        return winSize - 1;
    }

    public boolean hasCount(Map<Character, Integer> charCounts, int count) {
        for (char ch : charCounts.keySet()) {
            if (charCounts.get(ch) >= count) {
                return true;
            }
        }
        return false;
    }
}

class SolutionDontDoItLikeThis { // use left pointer-based sliding window code
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> counts = new HashMap<>();
        int winSt = 0;
        int winSize = 0;
        while (winSt + winSize < s.length()) {

            while (winSt + winSize <= s.length() && (winSize - maxFreq(counts) <= k)) {
                char addedChar = (winSt + winSize == s.length()) ? '#' : s.charAt(winSt + winSize); // hack for corner cases when we fully extend window to the end of string
                int addedCharCount = counts.getOrDefault(addedChar, 0) + 1;
                counts.put(addedChar, addedCharCount);
                winSize++;
            } // when we exit while, window becomes invalid size, so we need to move it further and validate this size further

            if (winSt + winSize + 1 >= s.length()) {
                break;
            }

            char removedChar = s.charAt(winSt);
            int removedCharCount = counts.get(removedChar) - 1;
            counts.put(removedChar, removedCharCount);
            winSt++;
            char addedChar = s.charAt(winSt + winSize - 1);
            int addedCharCount = counts.getOrDefault(addedChar, 0) + 1;
            counts.put(addedChar, addedCharCount);
        }
        return winSize - 1;
    }

    public int maxFreq(Map<Character, Integer> charCounts) {
        int maxFreq = 0;
        for (char ch : charCounts.keySet()) {
            maxFreq = Math.max(maxFreq, charCounts.get(ch));
        }
        return maxFreq;
    }
}

class SolutionBetter { // right pointer-based sliding window code
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        int l = 0;
        int r = 0;
        int maxLength = 0;
        for (; r < s.length(); r++){ // move and validate window
            var addedChar = s.charAt(r);
            charCount.put(addedChar, charCount.getOrDefault(addedChar, 0) + 1);

            if (r - l + 1 > maxFreq(charCount) + k){ // window size is bigger then max freq + k (possible replacements)
                // => window is invalid, need to schrink, cause on prev step(iteration) it was valid
                var removedChar = s.charAt(l);
                charCount.put(removedChar, charCount.get(removedChar) - 1);
                l++;
            }

            if (r == s.length() - 1){
                maxLength = r - l + 1;
            }
        }
        return maxLength;
    }

    public int maxFreq(Map<Character, Integer> charCounts) {
        int maxFreq = 0;
        for (char ch : charCounts.keySet()) {
            maxFreq = Math.max(maxFreq, charCounts.get(ch));
        }
        return maxFreq;
    }
}


class SolutionBest { // right pointer-based sliding window code + trick
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        int l = 0;
        int r = 0;
        int maxFreq = 0;
        int maxLength  = 0;
        for (; r < s.length(); r++){ // move and validate window
            var addedChar = s.charAt(r);
            int charFreq = charCount.getOrDefault(addedChar, 0) + 1;
            charCount.put(addedChar, charCount.getOrDefault(addedChar, 0) + 1);
            maxFreq = Math.max(maxFreq, charFreq); //we only need track maxFreq, cause window size can be increased if only one maxFreq (winSize - k)

            // we can write
            // if (r - l + 1 <= maxFreq + k) continue; => if (winSize <= maxFreq + constant) i.e, if maxFreq doesn't change, winSize cannot grow, and maxFreq can only change on adding characters

            if (r - l + 1 > maxFreq + k){ // window size is bigger then max freq + k (possible replacements)
                // => window is invalid, need to schrink, cause on prev step(iteration) it was valid
                var removedChar = s.charAt(l);
                charCount.put(removedChar, charCount.get(removedChar) - 1);
                l++;
            }

            if (r == s.length() - 1){
                maxLength = r - l + 1;
            }
        }
        return maxLength;
    }
}

class Check {
    public static void main(String[] args) {
//        var r1 = new SolutionBetter().characterReplacement("AABABBA", 1);
        var r2 = new SolutionBetter().characterReplacement("ABAB", 2);
        var rr = 0;
    }
}
