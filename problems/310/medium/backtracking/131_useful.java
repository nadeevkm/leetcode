import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {

    List<List<String>> res = new LinkedList<>();

    public List<List<String>> partition(String s) {
        partition(0, 0, new ArrayList<String>(), s, 0);
        return res;
    }

    private void partition(int st, int end, List<String> currList, String s, int total){
        if (end >= s.length()){
            if (total == s.length()){
                res.add(new LinkedList<>(currList));
            }
            return;
        }

        if (isPalindrome(st, end, s)){
            currList.add(s.substring(st, end + 1));
            partition(end + 1, end + 1, currList, s, total + (end - st + 1));
            currList.remove(currList.size() - 1);
        }

        partition(st, end + 1, currList, s, total);
    }


    private boolean isPalindrome(int st, int end, String s){
        while (st < end){
            if (s.charAt(st) != s.charAt(end)){
                return false;
            }
            st++;
            end--;
        }
        return true;
    }
}


class SolutionOpt { // we can omit totalCh count, and its better to check if palindrome before substr, will speed up
    List<List<String>> res = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backtrack(0, new ArrayList<String>(), s);
        return res;
    }

    public void backtrack(int start, List<String> cand, String s) {
        if (start == s.length()) {
            res.add(List.copyOf(cand));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                var sstr = s.substring(start, i + 1);
                cand.add(sstr);
                backtrack(i + 1, cand, s);
                cand.remove(cand.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int p1, int p2) {
        while (p1 <= p2) {
            if (s.charAt(p1) != s.charAt(p2)) {
                return false;
            }
            p1++;
            p2--;
        }
        return true;
    }
}