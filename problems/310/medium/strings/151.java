class SolutionInitial {
    public String reverseWords(String s) {
        var sb = new StringBuffer(s);
        reverse(sb, 0, sb.length() - 1);

        int left = 0;
        int word = 0;
        int curP = 0;

        while (curP < sb.length()){
            while(curP < sb.length() && sb.charAt(curP) == ' '){
                curP++;
            }

            while (curP < sb.length() && sb.charAt(curP) != ' '){
                if (left != 0 && left == word){
                    sb.setCharAt(left, ' ');
                    left++;
                    word++;
                }
                char w = sb.charAt(curP);
                sb.setCharAt(word, w);
                word++;
                curP++;
            }

            if (word != left) {
                reverse(sb, left, word - 1);
                left = word;
            }
        }

        if (word < sb.length()) {
            sb.delete(word, sb.length());
        }

        return sb.toString();
    }

    public void reverse(StringBuffer sb, int st, int end){
        while (st < end){ // <---- made a serious error here !!! was st != end
            char l = sb.charAt(st);
            char r = sb.charAt(end);
            sb.setCharAt(st, r);
            sb.setCharAt(end, l);
            st++;
            end--;
        }
    }
}

class Solution {
    public String reverseWords(String s) {
        var chars = s.toCharArray();
        // reverse whole string
        reverse(chars, 0, chars.length - 1);
        Integer st = null;
        // reverse letters in words (to gain original words)
        for (int i = 0; i <= chars.length; i++) {
            if (i == chars.length || chars[i] == ' ') {
                if (st != null) {
                    reverse(chars, st, i - 1);
                    st = null;
                }
            } else { // != ' '
                if (st == null) {
                    st = i;
                }
            }
        }
        var sb = new StringBuilder().append(chars);
        // clean spaces
        cleanSpaces(sb);
        return sb.toString();
    }

    private void cleanSpaces(StringBuilder sb) {
        while (sb.charAt(0) == ' ') {
            sb.deleteCharAt(0);
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        int p = 0;
        int spCnt = 0;
        while (p < sb.length()){
            if (sb.charAt(p) == ' ') {
                if (spCnt > 0) {
                    sb.deleteCharAt(p);
                } else {
                    spCnt++;
                    p++;
                }
            } else{
                spCnt = 0;
                p++;
            }
        }
    }

    private void reverse(char[] s, int st, int end) {
        while (st < end) {
            char tmp = s[st];
            s[st] = s[end];
            s[end] = tmp;
            st++;
            end--;
        }
    }
}



// !! better to do cleaning and second reversing by single pass
class SolutionMoreConcise {
    public String reverseWords(String s) {
        var sb = new StringBuilder(s);
        sb.reverse();
        int l = 0;
        while(l < sb.length() && sb.charAt(l) == ' '){
            sb.deleteCharAt(l);
        }
        while (l < sb.length()) {
            int r = l;
            while (r + 1 < sb.length() && sb.charAt(r + 1) != ' ') {
                r++;
            }
            reverse(sb, l, r);
            l = r + 1;
            while (l < sb.length() && l + 1 < sb.length() && sb.charAt(l + 1) == ' ') {
                sb.deleteCharAt(l);
            }
            l++;
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' '){
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private void reverse(StringBuilder sb, int l, int r){
        while (l < r){
            char tmp1 = sb.charAt(l);
            char tmp2 = sb.charAt(r);
            sb.setCharAt(l, tmp2);
            sb.setCharAt(r, tmp1);
            l++;
            r--;
        }
    }
}