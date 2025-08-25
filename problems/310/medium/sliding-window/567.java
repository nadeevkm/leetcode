import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> target = new HashMap<>();
        for (var ch : s1.toCharArray()) {
            target.put(ch, target.getOrDefault(ch, 0) + 1);
        }

        int l = 0;
        int r = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (; r < s2.length(); r++) {

            char added = s2.charAt(r);
            window.put(added, window.getOrDefault(added, 0) + 1);

            if (r - l + 1 > s1.length()) {
                char removed = s2.charAt(l);
                int removedCount = window.get(removed) - 1;
                if (removedCount == 0) {
                    window.remove(removed);
                } else {
                    window.put(removed, removedCount);
                }
                l++;
            }

            if (window.equals(target)) {
                return true;
            }
        }
        return false;
    }
}

class Solution2 { // without map equals (which is formally O(26n)), use match count
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> target = new HashMap<>();
        for (var ch : s1.toCharArray()) {
            target.put(ch, target.getOrDefault(ch, 0) + 1);
        }

        int l = 0;
        int r = 0;
        int matchCount = 0;
        Map<Character, Integer> window = new HashMap<>();
        for (; r < s2.length(); r++) {

            char added = s2.charAt(r);
            int addedCount = window.getOrDefault(added, 0) + 1;
            window.put(added, addedCount);
            if (target.containsKey(added) && target.get(added) == addedCount){
                matchCount++;
            }

            if (r - l + 1 > s1.length()) {
                char removed = s2.charAt(l);
                if (target.containsKey(removed) && target.get(removed) == window.get(removed)){
                    matchCount--;
                }
                int removedCount = window.get(removed) - 1;
                window.put(removed, removedCount);
                l++;
            }

            if (matchCount == target.keySet().size()) {
                return true;
            }
        }
        return false;
    }
}


class Solution3 { // use match count, arrays instead of maps
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] target = new int[26];
        int uniqChars = 0;
        for (var ch : s1.toCharArray()) {
            if (target[ch - 'a'] == 0){
                uniqChars++;
            }
            target[ch - 'a']++;
        }

        int l = 0;
        int r = 0;
        int matchCount = 0;
        int[] window = new int[26];
        for (; r < s2.length(); r++) {

            char added = s2.charAt(r);
            window[added - 'a']++;
            if (target[added - 'a'] == window[added - 'a']){
                matchCount++;
            }

            if (r - l + 1 > s1.length()) {
                char removed = s2.charAt(l);
                if (target[removed - 'a'] == window[removed - 'a']){
                    matchCount--;
                }
                window[removed - 'a']--;
                l++;
            }

            if (matchCount == uniqChars) {
                return true;
            }
        }
        return false;
    }
}

class Solution4 { // use match count, arrays instead of maps - with one array
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] target = new int[26];
        int uniqChars = 0;
        for (var ch : s1.toCharArray()) {
            if (target[ch - 'a'] == 0){
                uniqChars++;
            }
            target[ch - 'a']++;
        }

        int l = 0;
        int r = 0;
        int matchUniqCharsCount = 0;
        for (; r < s2.length(); r++) {

            char added = s2.charAt(r);
            target[added - 'a']--;
            if (target[added - 'a'] == 0){
                matchUniqCharsCount++;
            }

            if (r - l + 1 > s1.length()) {
                char removed = s2.charAt(l);
                if (target[removed - 'a'] == 0){
                    matchUniqCharsCount--;
                }
                target[removed - 'a']++;
                l++;
            }

            if (matchUniqCharsCount == uniqChars) {
                return true;
            }
        }
        return false;
    }
}

class Solution5 { // use match count, arrays instead of maps - with one array, use match (all) chars count, not
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] target = new int[26];
        for (var ch : s1.toCharArray()) {
            target[ch - 'a']++;
        }

        int l = 0;
        int r = 0;
        int matchCharsCount = 0;
        for (; r < s2.length(); r++) {

            char added = s2.charAt(r);
            if (target[added - 'a'] > 0){ //means 'added' is desired and provided
                matchCharsCount++;
            }
            target[added - 'a']--;

            if (r - l + 1 > s1.length()) {
                char removed = s2.charAt(l);
                if (target[removed - 'a'] >= 0){ // means we dropped the desired letter ('removed')
                    matchCharsCount--;
                }
                target[removed - 'a']++;
                l++;
            }

            if (matchCharsCount == s1.length()) {
                return true;
            }
        }
        return false;
    }
}

class SolutionNotCleanWithDiffDoNotLook { // add 'matches' count in order not to check whole hashMap every time, swith to arrays insteadof maps
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }


        int[] target = new int[26];
        for (var ch : s1.toCharArray()) {
            target[ch - 'a']++;
        }

        int l = 0;
        int r = 0;

        int[] window = new int[26];
        for (; r < s1.length(); r++) {
            var ch = s2.charAt(r);
            window[ch - 'a']++;
        }

        int diff = 0;
        for (int i = 0; i < window.length; i++) {
            if (target[i] != window[i]) {
                diff++;
            }
        }
        if (diff == 0) {
            return true;
        }

        for (; r < s2.length(); r++) {
            char added = s2.charAt(r);
            if (window[added - 'a'] == target[added - 'a']) {
                diff++;
            }
            window[added - 'a']++;
            if (window[added - 'a'] == target[added - 'a']) {
                diff--;
            }

            char removed = s2.charAt(l);
            if (window[removed - 'a'] == target[removed - 'a']) {
                diff++;
            }
            window[removed - 'a']--;
            if (window[removed - 'a'] == target[removed - 'a']) {
                diff--;
            }
            l++;

            if (diff == 0) {
                return true;
            }
        }
        return false;
    }
}


class Check{
    public static void main(String[] args) {
//        var r1 = new SolutionOptim().checkInclusion("ab", "eidboaoo");
        var r2 = new Solution().checkInclusion("ab", "eidbaooo");
        var rr = 0;
    }
}


class SolutionLatest {
    public boolean checkInclusion(String s1, String s2) {
        var reqChars = new HashMap<Character, Integer>();
        for (var ch : s1.toCharArray()) {
            reqChars.put(ch, reqChars.getOrDefault(ch, 0) + 1);
        }

        int l = 0;
        int r = 0;
        int foundChars = 0;
        for (; r < s2.length(); r++) {
            var chToAdd = s2.charAt(r);
            if (reqChars.containsKey(chToAdd)) {
                int reqCount = reqChars.get(chToAdd);
                if (reqCount > 0) {
                    foundChars++;
                }
                reqChars.put(chToAdd, reqCount - 1);
            }

            if (r - l + 1 > s1.length()) {
                var chToRemove = s2.charAt(l);
                if (reqChars.containsKey(chToRemove)) {
                    int reqCount = reqChars.get(chToAdd);
                    reqChars.put(chToRemove, reqCount + 1);
                    if (reqCount > 0) {
                        foundChars--;
                    }
                }
                l++;
            }

            if (foundChars == s1.length()) {
                return true;
            }
        }
        return false;
    }
}