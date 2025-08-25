import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String longestPalindrome(String s) {  // O(n^2)
        int longest = 1;
        int start = 0;
        for (int i = 0; i < s.length(); i++){
            int l = i;
            int r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                if (r - l + 1 > longest){
                    longest = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                if (r - l + 1 > longest){
                    longest = r - l + 1;
                    start = l;
                }
                l--;
                r++;
            }
        }
        return s.substring(start, start + longest);
    }
}

class SolutionDp { // get last char, check for equal chars before and palindromes within, not optimal, it is O(n^3) , but we can better, check following solution
    // can improve to O(n^2) if switch List to Set
    public String longestPalindrome(String s) {
        List<Integer>[] mem = new List[s.length()];
        Arrays.setAll(mem, i -> new ArrayList<>(Arrays.asList(0, 1)));
//        mem[0].add(1);
//        mem[0].add(0);
        int longest = 1;
        int start = 0;
        for (int i = 1; i < s.length(); i++){
            var lastCh = s.charAt(i);
//            mem[i].add(0);
//            mem[i].add(1);
            for (int j = 0; j < i; j++){
                var currCh = s.charAt(j);
                if (lastCh == currCh){
                    for (var palLen : mem[i - 1]){
                        if (palLen == i - j - 1){
                            mem[i].add(i - j + 1);
                            if (i - j + 1  > longest){
                                longest = i - j + 1;
                                start = j;
                            }
                        }
                    }
                }
            }
        }
        return s.substring(start, start + longest);
    }
}


class SolutionDpOpt { // O(n^2), for each char check all prev palindrome lengths and this last char with char before palindrome
    public String longestPalindrome(String s) {
        List<Integer>[] mem = new List[s.length()];
        Arrays.setAll(mem, i -> new ArrayList<>(Arrays.asList(0, 1)));
        int longest = 1;
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            var lastCh = s.charAt(i);
            for (var palLen : mem[i - 1]) {
                if (i - palLen == 0) {
                    continue;
                }
                var opposChar = s.charAt(i - palLen - 1);
                if (opposChar == lastCh) {
                    mem[i].add(palLen + 2);
                    if (palLen + 2 > longest) {
                        longest = palLen + 2;
                        start = i - palLen - 1;
                    }
                }
            }
        }
        return s.substring(start, start + longest);
    }
}