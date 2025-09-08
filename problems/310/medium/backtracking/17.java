import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        String[] lettersMapping = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        return combine(new ArrayList<String>(), lettersMapping, new char[digits.length()], 0, digits);
    }

    public List<String> combine(List<String> overallList, String[] lettersMapping, char[] currComb, int curr, String digits){
        if (curr == digits.length()){
            if (curr > 0) {
                overallList.add(new String(currComb));
            }
        } else {
            for (char c : lettersMapping[digits.charAt(curr) - '0'].toCharArray()){
                currComb[curr] = c;
                curr++;
                combine(overallList, lettersMapping, currComb, curr, digits);
                curr--;
            }
        }

        return overallList;
    }
}

class SolutionRepetit {
    private Map<Character, String> letters = Map.of(
            '2',
            "abc",
            '3',
            "def",
            '4',
            "ghi",
            '5',
            "jkl",
            '6',
            "mno",
            '7',
            "pqrs",
            '8',
            "tuv",
            '9',
            "wxyz");

    private List<String> res = new LinkedList<>();

    public List<String> letterCombinations(String digits) {
        if (!digits.isBlank()) {
            dfs(0, digits, new StringBuilder());
        }
        return res;
    }

    private void dfs(int pos, String digits, StringBuilder curr) {
        if (pos == digits.length()) {
            res.add(curr.toString());
            return;
        }

        for (var ch : letters.get(digits.charAt(pos)).toCharArray()) {
            curr.append(ch);
            dfs(pos + 1, digits, curr);
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}


class Check{
    public static void main(String[] args) {
        var pp = new Solution().letterCombinations("23");
    }
}