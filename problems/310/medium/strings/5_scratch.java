import java.util.LinkedList;
import java.util.List;


class Solution3 {
    //
    //    a b b a
    //  a t
    //  b   t t
    //  b     t
    //  a       t
    public String longestPalindrome(String s) { // DP, axe X - start letters, axe Y - end letters
        boolean[][] mem = new boolean[s.length()][s.length()];
        int longestStart = 0;
        int longestEnd = 0;

        // fill 1-lettered palindromes
        for (int i = 0; i < s.length(); i++) {
            mem[i][i] = true;
        }

        // fill 2-lettered palindromes
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i + 1) == s.charAt(i)) {
                mem[i][i + 1] = true;
                longestStart = i;
                longestEnd = i + 1;
            }
        }

        // 3, 4 .. up to s.length()
        for (int diff = 2; diff < s.length(); diff++) {
            for (int st = 0; st < s.length() - diff; st++) {
                int en = st + diff;
                if (mem[st + 1][en - 1] && s.charAt(st) == s.charAt(en)) {
                    mem[st][en] = true;
                    longestStart = st;
                    longestEnd = en;
                }
            }
        }

        return s.substring(longestStart, longestEnd + 1);
    }
}

class Solution {
    //
    //    c
    //   pn
    // "cbbd"
    //
    public String longestPalindrome(String s) { // dp-inspired, whenever adding character, check all palindroms ending on prev. char
        List<Integer>[] mem = new LinkedList[s.length()];
//        for (int i = 0; i < mem.length; i++){
//            mem[i] = new LinkedList<Integer>();
//        }
        mem[0] = new LinkedList<Integer>();
        mem[0].add(1);
        int[] largestPalInd = new int[2];
        for (int i = 1; i < s.length(); i++) {
            mem[i] = new LinkedList<Integer>();
            mem[i].add(1);
            if (s.charAt(i) == s.charAt(i - 1)) {
                mem[i].add(2);
                if (2 > largestPalInd[1] - largestPalInd[0] + 1) {
                    largestPalInd[1] = i;
                    largestPalInd[0] = i - 1;
                }
            }
            for (var pldrmLength : mem[i - 1]) {
                if (i - 1 - pldrmLength >= 0 && s.charAt(i - 1 - pldrmLength) == s.charAt(i)) {
                    int newPldrmLength = pldrmLength + 2;
                    mem[i].add(newPldrmLength);
                    if (newPldrmLength > largestPalInd[1] - largestPalInd[0] + 1) {
                        largestPalInd[1] = i;
                        largestPalInd[0] = i - newPldrmLength + 1;
                    }
                }
            }
        }
        return s.substring(largestPalInd[0], largestPalInd[1] + 1);
    }
}

class Solution2 {
    //
    //    c
    //   pn
    // "cbbd"
    //
    public String longestPalindrome(String s) {  // two pointers, find centre and expand
        if (s.length() == 1) {
            return s;
        }
        int prev, next;
        int curr = 1;
        StringBuilder largest = new StringBuilder();
        while (curr < s.length()) {
            StringBuilder evenLength = new StringBuilder();
            prev = curr - 1;
            next = curr;
            while (prev >= 0 && next < s.length() && s.charAt(next) == s.charAt(prev)) {
                evenLength.insert(0, s.charAt(prev));
                evenLength.append(s.charAt(next));
                prev--;
                next++;
            }
            if (evenLength.length() > largest.length()) {
                largest = evenLength;
            }
            prev = curr - 1;
            next = curr + 1;
            StringBuilder oddLength = new StringBuilder();
            oddLength.append(s.charAt(curr));
            while (prev >= 0 && next < s.length() && s.charAt(next) == s.charAt(prev)) {
                oddLength.insert(0, s.charAt(prev));
                oddLength.append(s.charAt(next));
                prev--;
                next++;
            }
            if (oddLength.length() > largest.length()) {
                largest = oddLength;
            }
            curr++;
        }
        return largest.toString();
    }
}

class Check {

    public static void main(String[] args) {
        var pp = new Solution().longestPalindrome("aaaaa");
        var ppp = 1;
    }
}